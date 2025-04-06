package com.example.payroll_service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.util.Optional;

@FeignClient(name = "EMPLOYEE-SERVICE", path = "/employees")
public interface EmployeeFeign {

    @GetMapping("/info/{employeeId}")
    public Optional<BigDecimal> getEmployeeSalary(@PathVariable Long employeeId);
}
