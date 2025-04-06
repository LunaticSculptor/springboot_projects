package com.example.jspdemo.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthenticationService {

    private final List<User> users = new ArrayList<>();

    public AuthenticationService() {
        // Adding a default user for initial testing
        users.add(new User("raghav", "123"));
    }

    public boolean authenticate(String username, String password) {
        return users.stream()
                .anyMatch(user -> user.getUsername().equalsIgnoreCase(username)
                        && user.getPassword().equals(password));
    }

    public boolean addUser(String username, String password) {
        if (users.stream().anyMatch(user -> user.getUsername().equalsIgnoreCase(username))) {
            return false; // User already exists
        }
        users.add(new User(username, password));
        return true;
    }

    // Inner class to represent a user
    private static class User {
        private final String username;
        private final String password;

        public User(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }
    }
}
