package com.example.user_service.service;

import com.example.user_service.dto.UserDTO;
import com.example.user_service.model.Roles;
import com.example.user_service.model.UserLogin;
import com.example.user_service.model.UserRegister;
import com.example.user_service.model.Users;
import com.example.user_service.repository.RolesRepo;
import com.example.user_service.repository.UsersRepo;
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

    public Page<UserDTO> getUsers(int page, int size, String role) {
        Page<Users> usersPage = userRepo.findByRole(role, PageRequest.of(page, size));

        return usersPage.map(user -> {
            UserDTO dto = new UserDTO();
            dto.setUserId(user.getUserId());
            dto.setName(user.getName());
            dto.setEmail(user.getEmail());
            dto.setRoles(user.getRoles().stream().map(roleObj -> roleObj.getRoleName()).collect(Collectors.toSet()));
            dto.setCreatedAt(user.getCreatedAt());
            return dto;
        });
    }

    public Optional<Users> getUserById(Integer id) {
        return userRepo.findById(id);
    }
}
