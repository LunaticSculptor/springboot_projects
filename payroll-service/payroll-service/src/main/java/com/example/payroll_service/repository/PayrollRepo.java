package com.example.payroll_service.repository;

import com.example.payroll_service.model.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PayrollRepo extends JpaRepository<Payroll, Long> {

    Optional<Payroll> findByEmployeeId(Long employeeId);

    boolean existsByEmployeeId(Long employeeId);
}
