package com.example.user_service.controller;

import com.example.user_service.dto.UserDTO;
import com.example.user_service.model.UserLogin;
import com.example.user_service.model.UserRegister;
import com.example.user_service.model.Users;
import com.example.user_service.service.UsersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.management.relation.RoleNotFoundException;
import java.util.Optional;


@RestController
//@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService userService;

    @PreAuthorize("hasRole('ROLE_HR')")
    @PostMapping("/register")
    public Users register(@Valid @RequestBody UserRegister userRegister) throws RoleNotFoundException {
        return userService.register(userRegister);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserLogin userLogin) {
        return userService.verify(userLogin, userLogin.getPassword());
    }

    @GetMapping("/getUsers")
    @PreAuthorize("hasAuthority('ROLE_HR')")
    public Page<UserDTO> getUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String role) {
        return userService.getUsers(page, size, role);
    }

    @GetMapping("/getUserById/{id}")
    public Optional<Users> getUserInfo(@PathVariable Integer id) {
        return userService.getUserById(id);
    }
}
