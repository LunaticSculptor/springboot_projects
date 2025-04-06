package com.example.employee_service.validation;

import com.example.employee_service.model.Employee;
import com.example.employee_service.validation.annotations.ValidDepartment;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DepartmentValidator implements ConstraintValidator<ValidDepartment, Employee.Department> {

    @Override
    public void initialize(ValidDepartment constraintAnnotation) {
    }

    @Override
    public boolean isValid(Employee.Department department, ConstraintValidatorContext context) {
        if (department == null) {
            return false;
        }
        return department == Employee.Department.ENGINEERING || department == Employee.Department.HR || department == Employee.Department.FINANCE;
    }
}
