package com.example.assessment4.controller;

import com.example.assessment4.model.UserLogin;
import com.example.assessment4.model.UserRegister;
import com.example.assessment4.model.Users;
import com.example.assessment4.service.UsersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.management.relation.RoleNotFoundException;
import java.util.List;


@RestController
//@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService userService;

    @PostMapping("/register")
    public Users register(@Valid @RequestBody UserRegister userRegister) throws RoleNotFoundException {
        return userService.register(userRegister);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserLogin userLogin) {
        return userService.verify(userLogin, userLogin.getPassword());
    }

    @GetMapping("/getUsers")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Users> getUsers() {
        return userService.getUsers();
    }
}
