package com.example.firstproject.controller;

import com.example.firstproject.model.LoginRequest;
import com.example.firstproject.model.User;
import com.example.firstproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins="http://localhost:5173")
@RestController
@RequestMapping("/auth")

public class AuthController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    @CrossOrigin(origins = "http://localhost:5173")
    public String register(
            @RequestBody User user
            ) throws Exception{
        userService.register(user);
         return "User Registered";
    }
    @PostMapping("/login")
    @CrossOrigin(origins = "http://localhost:5173")
    public String login(
            @RequestBody LoginRequest request
            ) throws Exception{
        return userService.login(
                request.getEmail(),
                request.getPassword()
        );
    }
}
