package com.example.crud.service;

import com.example.crud.model.Task;
import com.example.crud.model.User;
import com.example.crud.repository.TaskRepo;
import com.example.crud.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepo taskRepo;

    @Autowired
    private UserRepo userRepo;

    public List<Task> getAllTasks() {
        return taskRepo.findAll();
    }

    public Task addTask(Task task, int userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (taskRepo.existsByNameAndUserUserId(task.getName(), userId)) {
            throw new RuntimeException("Task with this name already exists for the user");
        }

        task.setUser(user);
        return taskRepo.save(task);
    }

    public List<Task> getTasksByUserId(int userId) {
        return userRepo.findById(userId).orElseThrow().getTasks();
    }
}
