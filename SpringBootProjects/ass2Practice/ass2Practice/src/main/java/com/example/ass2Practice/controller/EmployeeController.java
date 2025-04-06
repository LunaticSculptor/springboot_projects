package com.example.ass2Practice.controller;

import com.example.ass2Practice.exception.IdNotFoundException;
import com.example.ass2Practice.model.Employee;
import com.example.ass2Practice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping
    public ResponseEntity<Page<Employee>> getAllEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        return ResponseEntity.ok(employeeService.getAllEmployees(page, size, sortBy, direction));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) throws IdNotFoundException {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }
    @PostMapping
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) throws IdNotFoundException {
        return ResponseEntity.ok(employeeService.saveEmployee(employee));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateDepartment(@PathVariable Long id, @RequestBody Employee employee) throws IdNotFoundException {
        return ResponseEntity.ok(employeeService.updateEmployee(employee, id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) throws IdNotFoundException {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}
