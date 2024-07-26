package com.example.user_service.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RequestMapping("/users")
@RestController
public class UserController {
    
    @GetMapping("/status")
    public String getStatus() {
        return "User service is up";
    }
}
