package com.example.crud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JavaController {

    @GetMapping("/hi")
    public String sayHello() {
        return "Hello";
    }
}
