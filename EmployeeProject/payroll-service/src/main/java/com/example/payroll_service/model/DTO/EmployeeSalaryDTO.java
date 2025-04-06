package com.example.payroll_service.model.DTO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class EmployeeSalaryDTO {
    private BigDecimal salary;
}
