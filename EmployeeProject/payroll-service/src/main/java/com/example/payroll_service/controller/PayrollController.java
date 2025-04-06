package com.example.payroll_service.controller;

import com.example.payroll_service.exception.IdNotFoundException;
import com.example.payroll_service.model.DTO.PayrollGenerateDTO;
import com.example.payroll_service.model.Payroll;
import com.example.payroll_service.service.PayrollService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payroll")
public class PayrollController {

    private final PayrollService payrollService;

    @GetMapping
    public ResponseEntity<Page<Payroll>> getAllPayrolls(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "ASC") String direction,
            @RequestParam(defaultValue = "payrollId") String sortBy
    ) {
        return ResponseEntity.ok(payrollService.getAllPayrolls(page, size, direction, sortBy));
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<Payroll> getPayroll(@PathVariable Long employeeId) throws IdNotFoundException {
        return ResponseEntity.ok(payrollService.getPayroll(employeeId));
    }

    @PostMapping("/generate")
    public ResponseEntity<Payroll> generatePayroll(@RequestBody PayrollGenerateDTO payrollGenerateDTO) throws IdNotFoundException {
        return new ResponseEntity<>(payrollService.generatePayroll(payrollGenerateDTO), HttpStatus.CREATED);
    }
}
