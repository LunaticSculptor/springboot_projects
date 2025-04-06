package com.example.assessment4.repo;

import com.example.assessment4.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepo extends JpaRepository<Roles, Integer> {
    Roles findByRoleName(String roleName);
}
