package com.example.demoSecurity1.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String hello() {
        return "Hello, World!";
    }

    @GetMapping("/admin")
    public String helloAdmin() {
        return "Hello, World Admin!";
    }

    @GetMapping("/user")
    public String helloUser() {
        return "Hello, World User+Admin!";
    }

    @GetMapping("/public")
    public String helloPublic() {
        return "Hello, World Public!";
    }

    // Method secured with @PreAuthorize
    @GetMapping("/secured-admin")
    @PreAuthorize("hasRole('ADMIN')") // Only ADMIN can access
    public String securedAdmin() {
        return "Hello, Secured Admin!";
    }

    // Method secured with @PreAuthorize
    @GetMapping("/secured-user")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')") // USER or ADMIN can access
    public String securedUser() {
        return "Hello, Secured User!";
    }

    // Method secured with @Secured
    @GetMapping("/secured-role-admin")
    @Secured("ROLE_ADMIN") // Only ADMIN can access
    public String securedRoleAdmin() {
        return "Hello, Secured Role-based Admin!";
    }

    // Method secured with @Secured
    @GetMapping("/secured-role-user")
    @Secured({"ROLE_USER", "ROLE_ADMIN"}) // USER or ADMIN can access
    public String securedRoleUser() {
        return "Hello, Secured Role-based User!";
    }
}
