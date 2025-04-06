package com.example.crud.controller;

import com.example.crud.model.Task;
import com.example.crud.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> getTasks() {
        return new ResponseEntity<>(taskService.getAllTasks(), HttpStatus.OK);
    }

    @PostMapping("/users/{userId}/tasks")
    public ResponseEntity<?> addTask(@RequestBody Task task, @PathVariable int userId) {
        try {
            Task newTask = taskService.addTask(task, userId);
            return new ResponseEntity<>(newTask, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/users/{userId}/tasks")
    public ResponseEntity<?> getTasksByUserId(@PathVariable int userId) {
        try {
            return new ResponseEntity<>(taskService.getTasksByUserId(userId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
