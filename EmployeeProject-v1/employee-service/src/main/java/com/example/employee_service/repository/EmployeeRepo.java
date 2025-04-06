package com.example.employee_service.repository;

import com.example.employee_service.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {

    @Query("SELECT e.salary FROM Employee e WHERE e.employeeId = :id")
    Optional<BigDecimal> findSalaryByEmployeeId(@Param("id") Long id);
}
