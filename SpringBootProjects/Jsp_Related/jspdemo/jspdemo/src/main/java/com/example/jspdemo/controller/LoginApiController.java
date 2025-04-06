package com.example.jspdemo.controller;

import com.example.jspdemo.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LoginApiController {

    private final AuthenticationService authenticationService;

    public LoginApiController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        if (authenticationService.authenticate(loginRequest.getName(), loginRequest.getPassword())) {
            return ResponseEntity.ok(new LoginResponse("Login successful", loginRequest.getName()));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse("Invalid credentials"));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        if (authenticationService.addUser(registerRequest.getName(), registerRequest.getPassword())) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new SuccessResponse("User registered successfully"));
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ErrorResponse("User already exists"));
        }
    }

    // DTO class for JSON request
    static class LoginRequest {
        private String name;
        private String password;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    static class RegisterRequest {
        private String name;
        private String password;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    // DTO classes for JSON responses
    static class LoginResponse {
        private String message;
        private String name;

        public LoginResponse(String message, String name) {
            this.message = message;
            this.name = name;
        }

        public String getMessage() {
            return message;
        }

        public String getName() {
            return name;
        }
    }

    static class SuccessResponse {
        private String message;

        public SuccessResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    static class ErrorResponse {
        private String error;

        public ErrorResponse(String error) {
            this.error = error;
        }

        public String getError() {
            return error;
        }
    }
}
