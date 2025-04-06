package com.example.employee_service.validation.annotations;

import com.example.employee_service.validation.DecimalValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = DecimalValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDecimal {
    String message() default "Salary must have up to 2 decimal places";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    int scale() default 2;
}
