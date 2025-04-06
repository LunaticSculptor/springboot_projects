package com.example.user_service.config;

import com.example.user_service.model.Roles;
import com.example.user_service.model.Users;
import com.example.user_service.repository.RolesRepo;
import com.example.user_service.repository.UsersRepo;
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
        return args -> {
            // Check if the ADMIN role exists, otherwise create and persist it
            Roles adminRole = rolesRepo.findByRoleName("ADMIN")
                    .orElseGet(() -> {
                        Roles newRole = new Roles();
                        newRole.setRoleName("ADMIN");
                        return rolesRepo.save(newRole); // Save it first
                    });

            // Ensure the admin user is created only if it does not exist
            if (!usersRepo.existsByEmail("admin@example.com")) {
                Users adminUser = new Users();
                adminUser.setName("Admin");
                adminUser.setEmail("admin@example.com");
                adminUser.setPassword(encoder.encode("Admin@123"));

                // Save the user AFTER ensuring role is persisted
                adminUser.setRoles(Set.of(adminRole));
                usersRepo.save(adminUser);
            }
        };
    }
}
