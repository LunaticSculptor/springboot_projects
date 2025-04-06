package com.example.auth_service.model;

import com.example.auth_service.annotations.UniqueEmail;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Set;

@RequiredArgsConstructor
@Getter
@Setter
public class UserRegister {
    @NotNull(message = "Name cannot be empty")
    private String name;
    @UniqueEmail
    @Email
    @NotNull(message = "Email cannot be empty")
    private String email;
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
            message = "Password must have at least 8 characters, including 1 lowercase, 1 uppercase letter, 1 digit, and 1 special character (@#$%^&+=)."
    )
    @NotNull(message = "Password cannot be empty")
    private String password;
    @NotEmpty(message = "Roles cannot be empty")
    private Set<Roles> roles;
}
