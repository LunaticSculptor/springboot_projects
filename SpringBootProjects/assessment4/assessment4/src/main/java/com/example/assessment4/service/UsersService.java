package com.example.assessment4.service;

import com.example.assessment4.dto.UserDTO;
import com.example.assessment4.model.Roles;
import com.example.assessment4.model.UserLogin;
import com.example.assessment4.model.UserRegister;
import com.example.assessment4.model.Users;
import com.example.assessment4.repo.RolesRepo;
import com.example.assessment4.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import javax.management.relation.RoleNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UsersService {

    @Autowired
    private UsersRepo userRepo;

    @Autowired
    private RolesRepo rolesRepo;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtService jwtService;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public Users register(UserRegister userRegister) throws RoleNotFoundException {
        if (userRepo.findByEmail(userRegister.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email must be unique, please provide another email");
        }

        Users user = new Users();
        user.setName(userRegister.getName());
        user.setEmail(userRegister.getEmail());
        user.setPassword(encoder.encode(userRegister.getPassword()));

        Set<Roles> assignedRoles = new HashSet<>();
        for (Roles role : userRegister.getRoles()) {
            Roles existingRole = rolesRepo.findByRoleName(role.getRoleName());
            if (existingRole != null) {
                assignedRoles.add(existingRole);
            } else {
                throw new RoleNotFoundException("No such role: " + role.getRoleName());
            }
        }
        user.setRoles(assignedRoles);
        return userRepo.save(user);
    }

    public String verify(UserLogin userLogin, String rawPassword) {
        Users user = userRepo.findByEmail(userLogin.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("No such user exists. Check your email"));

        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getEmail(), rawPassword)
            );

            if (authentication.isAuthenticated()) {
                return jwtService.generateToken(user.getEmail(), user.getRoles(), user.getUserId());
            } else {
                throw new BadCredentialsException("Wrong password");
            }
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid email or password");
        }
    }

    public List<Users> getUsers() {
        return userRepo.findAll();
    }
}
