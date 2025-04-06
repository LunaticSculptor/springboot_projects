package com.example.ass2Practice.repository;

import com.example.ass2Practice.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepo extends JpaRepository<Users, Integer> {
    Users findByUsername(String username);
}
