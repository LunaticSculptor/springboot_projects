package com.example.employee_service.model;

import com.example.employee_service.validation.annotations.ValidDecimal;
import com.example.employee_service.validation.annotations.ValidDepartment;
import com.example.employee_service.validation.annotations.ValidRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@RequiredArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    @NotBlank(message = "Name cannot be blank")
    @Column(nullable = false)
    private String name;

    public enum Role {
        DEVELOPER,
        MANAGER,
        HR,
        EXECUTIVE
    }
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Role cannot be null and must be one of: DEVELOPER, MANAGER, HR, EXECUTIVE")
    @ValidRole
    @Column(nullable = false)
    private Role role;

    public enum Department {
        ENGINEERING,
        HR,
        FINANCE
    }
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Department cannot be null and must be one of: ENGINEERING, HR, FINANCE")
    @ValidDepartment
    @Column(nullable = false)
    private Department department;

    @NotNull(message = "Salary is required field cannot be empty")
    @Positive(message = "Salary must be greater than zero")
    @ValidDecimal
    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal salary;
}
