package com.example.rest_controller.controller;

import com.example.rest_controller.model.Student;
import com.example.rest_controller.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentRepo studentRepo;

    @GetMapping("/students")
    public List<Student> getStudents() {
        return studentRepo.findAll();
    }
}
