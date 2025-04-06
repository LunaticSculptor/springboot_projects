package com.example.employee_service.service;

import com.example.employee_service.exception.IdNotFoundException;
import com.example.employee_service.model.DTO.EmployeeUpdateDTO;
import com.example.employee_service.model.Employee;
import com.example.employee_service.repository.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepo employeeRepo;


    public Page<Employee> getAllEmployees(int page, int size, String direction, String sortBy) {
//        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name())
//                ? Sort.by(sortBy).ascending()
//                : Sort.by(sortBy).descending();
//        Pageable pageable = PageRequest.of(page, size, sort);
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        return employeeRepo.findAll(pageable);
    }

    public Employee getEmployee(Long id) throws IdNotFoundException {
        return employeeRepo.findById(id).orElseThrow(() -> new IdNotFoundException("Employee with id: "+ id + " doesn't exist"));
    }


    public Employee saveEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    public Employee updateEmployee(EmployeeUpdateDTO employee, Long id) throws IdNotFoundException {
        Employee existingEmployee = employeeRepo.findById(id).orElseThrow(() -> new IdNotFoundException("Employee with id: "+ id + " doesn't exist"));

        if (employee.getName() != null) {
            existingEmployee.setName(employee.getName());
        }
        if (employee.getRole() != null) {
            existingEmployee.setRole(employee.getRole());
        }
        if (employee.getDepartment() != null) {
            existingEmployee.setDepartment(employee.getDepartment());
        }
        if (employee.getSalary() != null) {
            existingEmployee.setSalary(BigDecimal.valueOf(employee.getSalary()));
        }
        return employeeRepo.save(existingEmployee);
    }

    public void deleteEmployee(Long id) throws IdNotFoundException {
        if(!employeeRepo.existsById(id)) {
            throw new IdNotFoundException("Employee with id: "+id+" doesn't exist");
        }
        employeeRepo.deleteById(id);
    }
}
