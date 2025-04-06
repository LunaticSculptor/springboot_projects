package com.example.review_service.repository;

import com.example.review_service.model.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepo extends JpaRepository<Review,Integer> {
    List<Review> findByEmployeeId(Integer userId);

    @Query("SELECT r.employeeId, AVG(r.rating) as avgRating " +
            "FROM Review r " +
            "GROUP BY r.employeeId")
    List<Object[]> findEmployeesByAverageRating(Pageable pageable);
}
