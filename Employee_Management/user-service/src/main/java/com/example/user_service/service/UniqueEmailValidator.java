package com.example.user_service.service;

import com.example.user_service.annotations.UniqueEmail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import com.example.user_service.repository.UsersRepo;

@RequiredArgsConstructor
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private final UsersRepo usersRepo;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return email != null && !usersRepo.existsByEmail(email);
    }
}

