package com.example.springboot.myFirstWebApp.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SayHelloController {

    @RequestMapping("/")

    public String sayHelloJSP() {
        return "hello";
    }
}
