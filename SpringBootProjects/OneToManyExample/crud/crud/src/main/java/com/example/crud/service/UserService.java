package com.example.crud.service;

import com.example.crud.aspect.LogExecutionTime;
import com.example.crud.event.NewUserEvent;
import com.example.crud.model.User;
import com.example.crud.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@Lazy
@ConditionalOnProperty(name = "app.feature.enabled", havingValue = "true")
public class UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @LogExecutionTime
    @Cacheable(value = "usersCache")
    public List<User> getAllUsers() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return userRepo.findAll();
    }

    public User getUserById(int id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User with ID " + id + " not found"));
    }
    @Async
    public CompletableFuture<User> addUser(User user) {
        User newuser = userRepo.save(user);
        eventPublisher.publishEvent(new NewUserEvent(this, newuser.getUserName()));
        return CompletableFuture.completedFuture(newuser);
    }

    public User updateUser(int id, User userDetails) {
        User existingUser = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User with ID " + id + " not found"));

        if (userDetails.getUserName() != null) {
            existingUser.setUserName(userDetails.getUserName());
        }
        if (userDetails.getPassword() != null) {
            existingUser.setPassword(userDetails.getPassword());
        }
        return userRepo.save(existingUser);
    }

    public void deleteUser(int id) {
        if (!userRepo.existsById(id)) {
            throw new RuntimeException("User with ID " + id + " not found");
        }
        userRepo.deleteById(id);
    }
}
