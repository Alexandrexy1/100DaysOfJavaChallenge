package com.example.review.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.review.Services.UserService;
import com.example.review.entities.User;

import org.springframework.web.bind.annotation.GetMapping;


@RequestMapping("/users")
@RestController
public class UserController {
    
    @Autowired
    private UserService service;

    @GetMapping("/users")
    public ResponseEntity<List<User>> findAll() {
        List<User> users = service.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    
}
