package com.example.myBook.exception;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;
import java.util.Optional;

public class NoBannedWordsValidator implements ConstraintValidator<GlobalExceptionHandler.NoBannedWords, String> {
    private static final List<String> bannedWords = List.of("bad", "evil", "worst");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
//        return bannedWords.stream().noneMatch(value::contains);
        Optional<String> invalidWord = bannedWords.stream()
                .filter(value::contains)
                .findFirst();

        if (invalidWord.isPresent()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Title contains prohibited word: " + invalidWord.get())
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}
