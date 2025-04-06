package com.example.review_service.model;

import jakarta.persistence.Id;
import lombok.Data;

//@Entity
@Data
public class Employee {

    @Id
    private Integer employeeId;

    private Integer managerId;
}
