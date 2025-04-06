package com.example.user_service.service;

import com.example.user_service.model.Roles;
import com.example.user_service.repository.RolesRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RolesService {

    private final RolesRepo roleRepo;
    public Roles addRole(Roles role) {
        return roleRepo.save(role);
    }
}
