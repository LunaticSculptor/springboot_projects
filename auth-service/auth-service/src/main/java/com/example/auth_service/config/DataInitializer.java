package com.example.auth_service.config;

import com.example.auth_service.model.Roles;
import com.example.auth_service.model.Users;
import com.example.auth_service.repository.RolesRepo;
import com.example.auth_service.repository.UsersRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final UsersRepo usersRepo;
    private final RolesRepo rolesRepo;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Bean
    public ApplicationRunner initializeAdminUser() {
        return args -> initializeData();
    }

    @Transactional // Ensures the entire process runs in a single transaction
    public void initializeData() {
        // Fetch or create the ADMIN role
        Roles adminRole = rolesRepo.findByRoleName("ADMIN")
                .orElseGet(() -> {
                    Roles newRole = new Roles();
                    newRole.setRoleName("ADMIN");
                    return rolesRepo.save(newRole);
                });

        Roles hrRole = rolesRepo.findByRoleName("HR")
                .orElseGet(() -> {
                    Roles newRole = new Roles();
                    newRole.setRoleName("HR");
                    return rolesRepo.save(newRole);
                });

        Roles employeeRole = rolesRepo.findByRoleName("EMPLOYEE")
                .orElseGet(() -> {
                    Roles newRole = new Roles();
                    newRole.setRoleName("EMPLOYEE");
                    return rolesRepo.save(newRole);
                });

        // Check if admin user exists, if not, create it
        if (!usersRepo.existsByEmail("admin1@example.com")) {
            Users adminUser = new Users();
            adminUser.setName("Admin1");
            adminUser.setEmail("admin1@example.com");
            adminUser.setPassword(encoder.encode("Admin1@123"));

            // Attach only managed entities
            adminUser.setRoles(Set.of(adminRole));

            usersRepo.save(adminUser);
        }

        if (!usersRepo.existsByEmail("humanr1@example.com")) {
            Users hrUser = new Users();
            hrUser.setName("hr1");
            hrUser.setEmail("humanr1@example.com");
            hrUser.setPassword(encoder.encode("HumanR1@123"));

            // Attach only managed entities
            hrUser.setRoles(Set.of(hrRole));

            usersRepo.save(hrUser);
        }

        if (!usersRepo.existsByEmail("employee1@example.com")) {
            Users employeeUser = new Users();
            employeeUser.setName("employee1");
            employeeUser.setEmail("employee1@example.com");
            employeeUser.setPassword(encoder.encode("Employee1@123"));

            // Attach only managed entities
            employeeUser.setRoles(Set.of(employeeRole));

            usersRepo.save(employeeUser);
        }
    }
}
