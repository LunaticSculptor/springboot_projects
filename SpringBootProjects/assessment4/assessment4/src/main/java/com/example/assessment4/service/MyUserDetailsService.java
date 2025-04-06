package com.example.assessment4.service;

import com.example.assessment4.model.UserPrincipal;
import com.example.assessment4.model.Users;
import com.example.assessment4.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) {
        Users user = userRepo.findByEmail(email)
                .orElseThrow(()->new BadCredentialsException("Invalid Credentials"));
        if (user == null) {
//            System.out.println("User not found");
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        return new UserPrincipal(user);
    }
}
