package com.example.employee_service.service;

import com.example.employee_service.exception.IdNotFoundException;
import com.example.employee_service.feign.ReviewFeign;
import com.example.employee_service.feign.UserFeign;
import com.example.employee_service.model.Employee;
import com.example.employee_service.model.EmployeeDTO;
import com.example.employee_service.model.Review;
import com.example.employee_service.model.Users;
import com.example.employee_service.repository.EmployeeRepo;
import lombok.RequiredArgsConstructor;
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
public class EmployeeService {

    private final EmployeeRepo employeeRepo;
    private final UserFeign userFeign;
    private final ReviewFeign reviewFeign;

    public Employee assignManager(Integer employeeId, Integer managerId) throws IdNotFoundException {
        // Fetch employee and manager user details
        Set<Integer> userIds = Set.of(employeeId, managerId);
        Map<Integer, Optional<Users>> userData = userFeign.getUsersByIds(userIds);
        Users employee = userData.get(employeeId)
                .orElseThrow(() -> new IdNotFoundException("Employee not found: "+employeeId));

        Users manager = userData.get(managerId)
                .orElseThrow(() -> new IdNotFoundException("Manager not found: "+managerId));

        // Validate if the manager has the "MANAGER" role
        boolean isManager = manager.getRoles().stream()
                .anyMatch(role -> "MANAGER".equalsIgnoreCase(role.getRoleName()));

        if (!isManager) {
            throw new RuntimeException("User with id "+managerId+" is not authorized as a Manager");
        }

        // Save the employee-manager relationship
        Employee employeeEntity = new Employee();
        employeeEntity.setEmployeeId(employeeId);
        employeeEntity.setManagerId(managerId);

        return employeeRepo.save(employeeEntity);
    }

    public Optional<Employee> getManager(Integer id) {
        return employeeRepo.findById(id);
    }

    public EmployeeDTO getEmployeeInfo(Integer employeeId) throws IdNotFoundException {
        Integer userId = getCurrentUserId();
        List<String> userRoles = getCurrentUserRoles();
        boolean isHR = userRoles.stream().anyMatch("HR"::equalsIgnoreCase);
        boolean isManager = userRoles.stream().anyMatch("MANAGER"::equalsIgnoreCase);
        boolean isAssignedManager = false;

//        System.out.println(userId+" "+isHR+" "+isManager);
        // Fetch employee details
        Employee employee = employeeRepo.findById(employeeId)
                .orElseThrow(() -> new IdNotFoundException("Employee not found"));

        // Check if the logged-in user is the assigned manager
        if (employee.getManagerId() != null) {
            isAssignedManager = userId.equals(employee.getManagerId());
        }

        // Authorization check
        if (!isHR && (!isManager && !isAssignedManager)) {
            throw new RuntimeException("You are not authorized to get details of this user");
        }

        // Fetch all reviews using Feign Client
        List<Review> reviews = reviewFeign.getMyReview(employeeId);

//        System.out.println(reviews);
        // Gather all required user IDs (manager + reviewers)
        Set<Integer> userIds = reviews.stream().map(Review::getReviewerId).collect(Collectors.toSet());
//        System.out.println(userIds);
        if (employee.getManagerId() != null && !userIds.contains(employee.getManagerId())) {
            userIds.add(employee.getManagerId());
        }
        if (!userIds.contains(employeeId)) {
            userIds.add(employee.getManagerId());
        }
        userIds.add(userId);

//        System.out.println(userIds);
        // Fetch user details via Feign Client
        Map<Integer, Optional<Users>> requiredUsers = userFeign.getUsersByIds(userIds);

        // Extract employee name
        String employeeName = requiredUsers.getOrDefault(employeeId, Optional.empty())
                .map(Users::getName)
                .orElse("Unknown Employee");

        // Extract manager details
        EmployeeDTO.ManagerDTO managerDTO = null;
        if (employee.getManagerId() != null) {
            managerDTO = requiredUsers.getOrDefault(employee.getManagerId(), Optional.empty())
                    .map(manager -> new EmployeeDTO.ManagerDTO(manager.getUserId(), manager.getName()))
                    .orElse(null);
        }

        // Convert reviews to DTO
        List<EmployeeDTO.ReviewDTO> reviewDTOs = reviews.stream()
                .map(review -> new EmployeeDTO.ReviewDTO(
                        requiredUsers.getOrDefault(review.getReviewerId(), Optional.empty())
                                .map(Users::getName)
                                .orElse("Unknown Reviewer"),
                        review.getRating(),
                        review.getComments(),
                        review.getReviewedAt()
                ))
                .toList();

        // Construct and return EmployeeDTO
        return new EmployeeDTO(employee.getEmployeeId(), employeeName, managerDTO, reviewDTOs);
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
