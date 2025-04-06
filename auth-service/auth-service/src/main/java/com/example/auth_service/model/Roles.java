package com.example.auth_service.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleId;

//    @UniqueElements(message = "role name must be unique")
    @Column(nullable = false, unique = true)
    private String roleName;
    public Roles(String roleName) {
        this.roleName = roleName;
    }
}
