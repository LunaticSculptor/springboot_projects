package com.example.employee_service.feign;

import com.example.employee_service.model.Review;
import com.example.employee_service.model.Users;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@FeignClient(name = "REVIEW-SERVICE")
public interface ReviewFeign {

    @GetMapping("/review/{id}")
    List<Review> getMyReview(@PathVariable Integer id);
}
