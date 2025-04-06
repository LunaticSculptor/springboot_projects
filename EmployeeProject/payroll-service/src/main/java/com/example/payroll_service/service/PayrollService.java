package com.example.payroll_service.service;

import com.example.payroll_service.exception.IdNotFoundException;
import com.example.payroll_service.feign.EmployeeFeign;
import com.example.payroll_service.model.DTO.EmployeeSalaryDTO;
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
    private final EmployeeFeign employeeFeign;

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

        BigDecimal salary = employeeFeign.getEmployeeSalary(employeeId).orElseThrow(()->new IdNotFoundException("No such Employee with id: "+employeeId+" exists"));

        payroll.setSalary(salary);
        payroll.setTaxDeductions(getTaxDeductions(salary));
        payroll.setNetSalary(getNetSalary(salary));
        payroll.setStatus(Payroll.Status.PROCESSED);
        payroll.setPayDate(LocalDateTime.now());

        return payrollRepo.save(payroll);
    }

    private BigDecimal getNetSalary(BigDecimal salary) {
        double deductions = getTaxDeductions(salary).doubleValue();
        double amount = salary.doubleValue();
        return BigDecimal.valueOf(amount-deductions);
    }

    public BigDecimal getTaxDeductions(BigDecimal salary) {
        double amount = salary.doubleValue();
        if(amount<50000) {
            return BigDecimal.valueOf(amount*0.1);
        }else{
            return BigDecimal.valueOf(amount*0.2);
        }
    }
}
