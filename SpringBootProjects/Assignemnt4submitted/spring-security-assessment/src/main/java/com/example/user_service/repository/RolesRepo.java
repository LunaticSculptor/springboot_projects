package com.example.user_service.repository;

import com.example.user_service.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepo extends JpaRepository<Roles, Integer> {
    Roles findByRoleName(String roleName);
}
