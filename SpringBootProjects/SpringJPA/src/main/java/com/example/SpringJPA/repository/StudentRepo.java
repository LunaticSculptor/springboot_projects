package com.example.SpringJPA.repository;

import com.example.SpringJPA.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {

    @Query("select s from Student s where s.name=?1")
    List<Student> findBySName(String name);

    List<Student> findByMarksGreaterThan(int marks);
}
