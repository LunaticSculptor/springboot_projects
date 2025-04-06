package com.example.crud.repository;

import com.example.crud.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepo extends JpaRepository<Task, Integer> {
    boolean existsByNameAndUserUserId(String name, int userId);
}


//    boolean existsByName(String name);