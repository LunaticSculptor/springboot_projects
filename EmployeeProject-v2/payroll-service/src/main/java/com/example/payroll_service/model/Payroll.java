package com.example.payroll_service.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@RequiredArgsConstructor
public class Payroll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long payrollId;

    @Column(nullable = false)
    private Long employeeId;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal salary;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal taxDeductions;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal netSalary;

    public enum Status {
        PENDING,
        PROCESSED,
        FAILED
    }
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime payDate;
}
