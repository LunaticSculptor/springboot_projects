package com.example.employee_service.controller;

import com.example.employee_service.exception.IdNotFoundException;
import com.example.employee_service.model.Employee;
import com.example.employee_service.model.EmployeeDTO;
import com.example.employee_service.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
//@RequiredArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/{employeeId}/assignManager/{managerId}")
    public ResponseEntity<Employee> assignManager(@PathVariable Integer employeeId, @PathVariable Integer managerId) throws IdNotFoundException {
        return new ResponseEntity<>(employeeService.assignManager(employeeId, managerId), HttpStatus.OK);
    }

    @GetMapping("/getInfo/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeInfo(@PathVariable Integer id) throws IdNotFoundException {
        return new ResponseEntity<>(employeeService.getEmployeeInfo(id), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_INTERNAL')")
    @GetMapping("/getManager/{id}")
    public Optional<Employee> getManager(@PathVariable Integer id) {
        return employeeService.getManager(id);
    }

}
