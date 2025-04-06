package com.example.user_service.controller;

import com.example.user_service.model.Roles;
import com.example.user_service.service.RolesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RolesController {

    private final RolesService roleService;

    @PostMapping("/addRole")
    public ResponseEntity<Roles> addRole(@RequestBody Roles role) {
        return new ResponseEntity<>(roleService.addRole(role), HttpStatus.OK);
    }
}
