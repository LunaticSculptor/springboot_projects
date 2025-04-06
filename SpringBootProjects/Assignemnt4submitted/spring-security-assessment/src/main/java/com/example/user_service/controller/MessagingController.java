package com.example.user_service.controller;

import com.example.user_service.dto.User;
import com.example.user_service.messageProducer.RabbitProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MessagingController {

    @Autowired
    private RabbitProducer rabbitProducer;
    @PostMapping("/message")
    public ResponseEntity<String> sendJsonMessage(@RequestBody User user) {
        rabbitProducer.sendJsonMessage(user);
        return ResponseEntity.ok("Json message sent to RabbitMQ-> " + user.toString());
    }
}
