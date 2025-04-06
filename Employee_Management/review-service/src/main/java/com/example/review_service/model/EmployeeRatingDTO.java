package com.example.review_service.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmployeeRatingDTO {
    // Getters & Setters
    private Long employeeId;
    private Double averageRating;

    public EmployeeRatingDTO(Long employeeId, Double averageRating) {
        this.employeeId = employeeId;
        this.averageRating = averageRating;
    }

}

