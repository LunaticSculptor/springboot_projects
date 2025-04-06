package com.example.ass2Practice.service;

import com.example.ass2Practice.exception.IdNotFoundException;
import com.example.ass2Practice.model.Department;
import com.example.ass2Practice.model.Employee;
import com.example.ass2Practice.repository.DepartmentRepository;
import com.example.ass2Practice.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    public Page<Employee> getAllEmployees(int page, int size, String sortBy, String direction) {
//        return employeeRepository.findAll();
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return employeeRepository.findAll(pageable);
    }

    public Employee getEmployeeById(Long id) throws IdNotFoundException {
        return employeeRepository.findById(id).orElseThrow(() -> new IdNotFoundException("Id not found"));
    }
    @Transactional
    public Employee saveEmployee(Employee employee) throws IdNotFoundException {
        Department emplDepartment = departmentRepository.findById(employee.getDepartment().getId())
                .orElseThrow(() -> new IdNotFoundException("Department not found"));
        employee.setDepartment(emplDepartment);
        return employeeRepository.save(employee);
    }
    @Transactional
    public Employee updateEmployee(Employee employee, Long id) throws IdNotFoundException {
        Employee existingEmp = employeeRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Employee with this id does not exist"));

        existingEmp.setName(employee.getName());
        existingEmp.setEmail(employee.getEmail());
        existingEmp.setSalary(employee.getSalary());

        if (employee.getDepartment() != null) {
            Department department = departmentRepository.findById(employee.getDepartment().getId())
                    .orElseThrow(() -> new IdNotFoundException("Department not found"));
            existingEmp.setDepartment(department);
        }
        return employeeRepository.save(existingEmp);
    }

    @Transactional
    public void deleteEmployee(Long id) throws IdNotFoundException {
        if(!employeeRepository.existsById(id)){
            throw new IdNotFoundException("Employee with this id does not exist");
        }employeeRepository.deleteById(id);
    }

}
