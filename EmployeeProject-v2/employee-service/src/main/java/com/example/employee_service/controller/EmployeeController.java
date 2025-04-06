package com.example.employee_service.controller;

import com.example.employee_service.exception.IdNotFoundException;
import com.example.employee_service.model.DTO.EmployeeUpdateDTO;
import com.example.employee_service.model.Employee;
import com.example.employee_service.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<Page<Employee>> getEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "ASC") String direction,
            @RequestParam(defaultValue = "employeeId") String sortBy
    ) {
        return ResponseEntity.ok(employeeService.getAllEmployees(page, size, direction, sortBy));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Long id) throws IdNotFoundException {
        return ResponseEntity.ok(employeeService.getEmployee(id));
    }

    @PreAuthorize("hasAnyAuthority('HR', 'ADMIN')")
//    @PreAuthorize("hasAnyRole('ROLE_HR', 'ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<Employee> saveEmployee(@Valid @RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority('HR', 'ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@RequestBody EmployeeUpdateDTO employee, @PathVariable Long id) throws IdNotFoundException {
        return new ResponseEntity<>(employeeService.updateEmployee(employee, id), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) throws IdNotFoundException {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAuthority('ROLE_INTERNAL')")
    @GetMapping("/info/{employeeId}")
    public Optional<BigDecimal> getEmployeeSalary(@PathVariable Long employeeId) {
        return employeeService.getEmployeeSalary(employeeId);
    }
}
