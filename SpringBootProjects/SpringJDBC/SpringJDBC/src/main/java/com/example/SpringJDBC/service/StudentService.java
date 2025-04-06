package com.example.SpringJDBC.service;

import com.example.SpringJDBC.model.Student;
import com.example.SpringJDBC.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private StudentRepo repo;

    public StudentRepo getStudentRepo() {
        return repo;
    }
    @Autowired
    public void setStudentRepo(StudentRepo studentRepo) {
        this.repo = studentRepo;
    }
    public void addStudent(Student s) {
        repo.save(s);
    }

    public List<Student> getStudents() {
        return repo.findAll();
    }
}
