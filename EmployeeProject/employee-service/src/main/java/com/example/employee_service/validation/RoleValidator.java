package com.example.employee_service.validation;

import com.example.employee_service.model.Employee;
import com.example.employee_service.validation.annotations.ValidRole;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RoleValidator implements ConstraintValidator<ValidRole, Employee.Role> {

    @Override
    public void initialize(ValidRole constraintAnnotation) {
    }

    @Override
    public boolean isValid(Employee.Role role, ConstraintValidatorContext context) {
        if (role == null) {
            return false;
        }
        return role == Employee.Role.DEVELOPER || role == Employee.Role.MANAGER || role == Employee.Role.HR || role == Employee.Role.EXECUTIVE;
    }
}
