package com.example.jspdemo.controller;

import com.example.jspdemo.service.AuthenticationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@SessionAttributes("name")
public class LoginController {

    private AuthenticationService authenticationService;

    public LoginController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @RequestMapping(value="/login", method=GET)
    public String gotoLoginPage() {
        return "login";
    }
    @RequestMapping(value="/login", method=POST)
    public String welcomePage(@RequestParam String name, @RequestParam String password,
                              ModelMap model) {
        if(authenticationService.authenticate(name, password)) {
            model.put("name", name);
            return "welcome";
        } else {
            model.put("error", "Invalid credentials");
            return "login";
        }
    }
}
