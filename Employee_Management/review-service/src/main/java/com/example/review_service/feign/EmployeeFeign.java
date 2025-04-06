package com.example.review_service.feign;

import com.example.review_service.model.Employee;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Optional;

@FeignClient(name = "EMPLOYEE-SERVICE", path="/employees")
public interface EmployeeFeign {

    @GetMapping("/getManager/{id}")
    Optional<Employee> getManager(@PathVariable Integer id);
}
