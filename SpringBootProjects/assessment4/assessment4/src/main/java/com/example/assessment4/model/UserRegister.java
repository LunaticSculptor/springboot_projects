package com.example.assessment4.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Set;

@RequiredArgsConstructor
@Getter
@Setter
public class UserRegister {
    private String name;
    private String email;
    private String password;
    private Set<Roles> roles;
}
