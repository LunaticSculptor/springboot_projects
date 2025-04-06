package com.example.assessment4.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.Set;

@Data
public class UserDTO {
    private Integer userId;
    private String name;
    private String email;
    private Set<String> roles;
    private LocalDateTime createdAt;
}
