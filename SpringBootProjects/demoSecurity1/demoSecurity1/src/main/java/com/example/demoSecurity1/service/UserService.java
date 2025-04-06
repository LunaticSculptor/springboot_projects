//package com.example.demoSecurity1.service;
//
//import com.example.demoSecurity1.model.Users;
//import com.example.demoSecurity1.repository.UserRepo;
//import com.example.demoSecurity1.util.JwtUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserService {
//
//    @Autowired
//    private JwtUtil jwtUtil;
//
//    @Autowired
//    AuthenticationManager authManager;
//
//    @Autowired
//    private UserRepo repo;
//
//
//    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
//
//    public Users register(Users user) {
//        user.setPassword(encoder.encode(user.getPassword()));
//        repo.save(user);
//        return user;
//    }
//
//    public String verify(Users user) {
//        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
//        if (authentication.isAuthenticated()) {
//            return jwtUtil.generateToken(user.getUsername());
//        } else {
//            return "fail";
//        }
//    }
//}
