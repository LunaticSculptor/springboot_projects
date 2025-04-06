package com.example.review_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reviewId;

    @Column(nullable = false)
    private Integer employeeId;

    @Column(nullable = false)
    private Integer reviewerId;

    private String comments;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime reviewedAt;

    @Column(nullable = false)
    @DecimalMin(value = "1.0", message = "Rating must be at least 1.0")
    @DecimalMax(value = "5.0", message = "Rating must be at most 5.0")
    private BigDecimal rating;

    public void setRating(BigDecimal rating) {
        if (rating != null && rating.remainder(BigDecimal.valueOf(0.5)).compareTo(BigDecimal.ZERO) != 0) {
            throw new IllegalArgumentException("Rating must be in steps of 0.5");
        }
        this.rating = rating;
    }

}

