package com.example.review_service.controller;

import com.example.review_service.exception.IdNotFoundException;
import com.example.review_service.model.Review;
import com.example.review_service.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/review/{id}")
    public ResponseEntity<Review> postReview(@RequestBody Review review, @PathVariable("id") Integer employeeId) throws IdNotFoundException {
        return new ResponseEntity<>(reviewService.saveReview(review, employeeId), HttpStatus.OK);
    }

    @PostMapping("/review")
    public ResponseEntity<Review> selfReview(@RequestBody Review review) {
        return new ResponseEntity<>(reviewService.selfReview(review), HttpStatus.OK);
    }

//    @PreAuthorize("hasAuthority('ROLE_HR')")
    @GetMapping("/employees")
    public ResponseEntity<List<Object[]>> getEmployees(
            @RequestParam(defaultValue = "5") int limit,
            @RequestParam(defaultValue = "true") boolean top) {
        return ResponseEntity.ok(reviewService.getEmployeesByAverageRating(limit, top));
    }

//    @PreAuthorize("hasAuthority('ROLE_INTERNAL')")
    @GetMapping("/review")
    public ResponseEntity<List<Review>> getMyReview() {
        return new ResponseEntity<>(reviewService.getMyReviews(), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_INTERNAL')")
    @GetMapping("/review/{id}")
    public List<Review> getMyReview(@PathVariable Integer id) {
        return reviewService.getReviews(id);
    }
}
