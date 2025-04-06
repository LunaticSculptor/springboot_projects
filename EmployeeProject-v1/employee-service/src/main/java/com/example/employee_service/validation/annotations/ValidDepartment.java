package com.example.employee_service.validation.annotations;

import com.example.employee_service.validation.DepartmentValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = DepartmentValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDepartment {
    String message() default "Invalid department. Must be one of: ENGINEERING, HR, FINANCE";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
