package com.example.auth_service.repository;

import com.example.auth_service.model.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepo extends JpaRepository<Users, Integer> {
    Optional<Users> findByEmail(String email);

    @Query("SELECT u FROM Users u JOIN u.roles r WHERE :roleName IS NULL OR r.roleName = :roleName")
    Page<Users> findByRole(@Param("roleName") String roleName, Pageable pageable);

//    @Query(value = """
//    SELECT DISTINCT u.* FROM users u
//    JOIN users_roles ur ON u.user_id = ur.users_user_id
//    JOIN roles r ON ur.roles_role_id = r.role_id
//    WHERE (:roleName IS NULL OR r.role_name = :roleName)
//""",
//            countQuery = """
//    SELECT COUNT(DISTINCT u.user_id) FROM users u
//    JOIN users_roles ur ON u.user_id = ur.users_user_id
//    JOIN roles r ON ur.roles_role_id = r.role_id
//    WHERE (:roleName IS NULL OR r.role_name = :roleName)
//""",
//            nativeQuery = true)
//    Page<Users> findByRole(@Param("roleName") String roleName, Pageable pageable);

    boolean existsByEmail(String email);
}
