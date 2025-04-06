package com.example.review_service.service;

import com.example.review_service.exception.IdNotFoundException;
import com.example.review_service.feign.EmployeeFeign;
import com.example.review_service.feign.UserFeign;
import com.example.review_service.model.Employee;
import com.example.review_service.model.Review;
import com.example.review_service.model.Users;
import com.example.review_service.repository.ReviewRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepo reviewRepo;
    private final UserFeign userFeign;
    private final EmployeeFeign employeeFeign;

    public Review saveReview(Review review, Integer employeeId) throws IdNotFoundException {
        Integer userId = getCurrentUserId();

        Set<Integer> userIds = Set.of(employeeId);
        Map<Integer, Optional<Users>> userData = userFeign.getUsersByIds(userIds);
        Users employee = userData.get(employeeId)
                .orElseThrow(() -> new IdNotFoundException("Employee not found: "+employeeId));

        List<String> userRoles = getCurrentUserRoles();
        boolean isHR = userRoles.stream().anyMatch("HR"::equalsIgnoreCase);
        boolean isManager = userRoles.stream().anyMatch("MANAGER"::equalsIgnoreCase);

        Employee relation = employeeFeign.getManager(employeeId).orElseThrow(()->new IdNotFoundException("Manager not found for this user: "+employeeId));
        boolean isAssignedManager = userId.equals(relation.getManagerId());

        if(isHR || (isManager && isAssignedManager)) {
            review.setReviewerId(userId);
            review.setEmployeeId(employeeId);
            return reviewRepo.save(review);
        } else {
            throw new RuntimeException("You are not authorized to review this user");
        }
    }

    public Review selfReview(Review review) {
        Integer userId = getCurrentUserId();
        review.setReviewerId(userId);
        review.setEmployeeId(userId);
        return reviewRepo.save(review);
    }

    public List<Review> getMyReviews() {
        Integer userId = getCurrentUserId();
        return reviewRepo.findByEmployeeId(userId);
    }

    public List<Review> getReviews(Integer id) {
        return reviewRepo.findByEmployeeId(id);
    }


    public List<Object[]> getEmployeesByAverageRating(int limit, boolean top) {
        Sort sort = top ? Sort.by(Sort.Order.desc("avgRating")) : Sort.by(Sort.Order.asc("avgRating"));
        Pageable pageable = PageRequest.of(0, limit, sort);
        return reviewRepo.findEmployeesByAverageRating(pageable);
    }

    public Integer getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof User) {
            User user = (User) authentication.getPrincipal();
            return Integer.parseInt(user.getUsername()); // Since userId is stored as username
        }

        throw new RuntimeException("User ID not found in Security Context");
    }

    public List<String> getCurrentUserRoles() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getAuthorities() != null) {
            return authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());
        }

        return List.of(); // Return an empty list if no roles are found
    }

}
