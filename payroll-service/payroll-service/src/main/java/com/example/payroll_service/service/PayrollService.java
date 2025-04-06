package com.example.payroll_service.service;

import com.example.payroll_service.exception.IdNotFoundException;
import com.example.payroll_service.model.DTO.PayrollGenerateDTO;
import com.example.payroll_service.model.Payroll;
import com.example.payroll_service.repository.PayrollRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PayrollService {

    private final PayrollRepo payrollRepo;

    public Page<Payroll> getAllPayrolls(int page, int size, String direction, String sortBy) {
//        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name())
//                ? Sort.by(sortBy).ascending()
//                : Sort.by(sortBy).descending();
//        Pageable pageable = PageRequest.of(page, size, sort);
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        return payrollRepo.findAll(pageable);
    }

    public Payroll getPayroll(Long employeeId) throws IdNotFoundException {
        return payrollRepo.findByEmployeeId(employeeId).orElseThrow(() -> new IdNotFoundException("No payroll exists for employee with id: "+employeeId));
    }

    public Payroll generatePayroll(PayrollGenerateDTO payrollGenerateDTO) throws IdNotFoundException {
        Long employeeId = payrollGenerateDTO.getEmployeeId();

        if(payrollRepo.existsByEmployeeId(employeeId)) {
            throw new IdNotFoundException("Payroll already exists for this employee with id: "+employeeId);
        }
        Payroll payroll = new Payroll();

        payroll.setEmployeeId(employeeId);

        // get salary from employeeId from the employee service
        double salary = 1000000.50;

        payroll.setSalary(BigDecimal.valueOf(salary));
        double deductions = getTaxDeductions(salary);
        payroll.setTaxDeductions(BigDecimal.valueOf(getTaxDeductions(salary)));
        payroll.setNetSalary(BigDecimal.valueOf(salary-deductions));
        payroll.setStatus(Payroll.Status.PROCESSED);
        payroll.setPayDate(LocalDateTime.now());

        return payrollRepo.save(payroll);
    }

    public double getTaxDeductions(double salary) {
        if(salary<50000) {
            return (0.1*salary);
        } else {
            return (0.2*salary);
        }
    }
}
