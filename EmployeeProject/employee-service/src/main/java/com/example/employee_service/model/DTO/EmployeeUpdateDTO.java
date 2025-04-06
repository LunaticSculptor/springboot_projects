package com.example.employee_service.model.DTO;

import com.example.employee_service.model.Employee;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeUpdateDTO {

    private String name;
    private Employee.Role role;
    private Employee.Department department;
    private Long salary;
}
