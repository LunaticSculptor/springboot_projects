package com.example.user_service.config;

import com.example.user_service.service.CustomAuthenticationEntryPoint;
import com.example.user_service.service.JwtRequestFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true) // Replaced deprecated @EnableGlobalMethodSecurity
public class SecurityConfig {

    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    public SecurityConfig(CustomAuthenticationEntryPoint customAuthenticationEntryPoint) {
        this.customAuthenticationEntryPoint = customAuthenticationEntryPoint;
    }
    @Bean
    public UserDetailsService userDetailsService() {
        // Replaced deprecated configure(AuthenticationManagerBuilder auth) with a UserDetailsService bean
        UserDetails employee = User.withUsername("employee1")
                .password(passwordEncoder().encode("Employee1@123"))
                .roles("EMPLOYEE")
                .build();

        UserDetails hr = User.withUsername("hr1")
                .password(passwordEncoder().encode("HumanR1@123"))
                .roles("HR")
                .build();

        UserDetails admin = User.withUsername("admin1")
                .password(passwordEncoder().encode("Admin1@123"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(employee, hr, admin);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        // Replaced authenticationManagerBean() method with AuthenticationConfiguration-based approach
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtRequestFilter jwtRequestFilter) throws Exception {
        // Replaced (HttpSecurity http) with (HttpSecurity http, JwtRequestFilter jwtRequestFilter) to avoid circular dependency
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/admin/**").hasRole("ADMIN") // Only ADMIN can access /admin/**
                        .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN") // USER or ADMIN can access /user/**
                        .requestMatchers("/public/**").permitAll() // Permit all to access /public/**
                        .requestMatchers("/users/login").permitAll() // Permit authentication endpoint
                        .anyRequest().authenticated() // All other requests require authentication
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(ex -> ex.authenticationEntryPoint(customAuthenticationEntryPoint)); // Custom error handling

        // Replaced configure(HttpSecurity http) method with a SecurityFilterChain bean
        return http.build();
    }
}