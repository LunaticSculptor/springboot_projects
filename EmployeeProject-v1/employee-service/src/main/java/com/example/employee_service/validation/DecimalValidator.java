package com.example.employee_service.validation;

import com.example.employee_service.validation.annotations.ValidDecimal;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.math.BigDecimal;

public class DecimalValidator implements ConstraintValidator<ValidDecimal, BigDecimal> {

    private int scale;

    @Override
    public void initialize(ValidDecimal constraintAnnotation) {
        this.scale = constraintAnnotation.scale();
    }

    @Override
    public boolean isValid(BigDecimal value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // @NotNull will handle null check
        }
        return value.scale() <= scale;
    }
}
