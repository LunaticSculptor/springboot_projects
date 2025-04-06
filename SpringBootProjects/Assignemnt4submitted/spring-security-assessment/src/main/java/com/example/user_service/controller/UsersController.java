package com.example.user_service.controller;

import com.example.user_service.model.UserLogin;
import com.example.user_service.model.UserRegister;
import com.example.user_service.model.Users;
import com.example.user_service.service.UsersService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.management.relation.RoleNotFoundException;
import java.util.List;

@RestController
public class UsersController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsersController.class);

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key.json}")
    private String routingKeyJson;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private UsersService userService;

    @PostMapping("/register")
    public Users register(@Valid @RequestBody UserRegister userRegister) throws RoleNotFoundException {
        LOGGER.info("User Registered message sent-> {}", userRegister.getEmail());
        rabbitTemplate.convertAndSend(exchange, routingKeyJson, userRegister.getEmail());
        return userService.register(userRegister);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserLogin userLogin) {
        LOGGER.info("User Login message sent-> {}", userLogin.getEmail());
        rabbitTemplate.convertAndSend(exchange, routingKeyJson, userLogin.getEmail());
        return userService.verify(userLogin, userLogin.getPassword());
    }

    @GetMapping("/getUsers")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Users> getUsers() {
        return userService.getUsers();
    }
}
