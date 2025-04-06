package com.example.auth_service.service;

import com.example.auth_service.annotations.UniqueEmail;
import com.example.auth_service.repository.UsersRepo;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private final UsersRepo usersRepo;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return email != null && !usersRepo.existsByEmail(email);
    }
}

