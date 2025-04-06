package com.example.employee_service.validation.annotations;

import com.example.employee_service.validation.RoleValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = RoleValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidRole {
    String message() default "Invalid role. Must be one of: DEVELOPER, MANAGER, HR, EXECUTIVE";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
