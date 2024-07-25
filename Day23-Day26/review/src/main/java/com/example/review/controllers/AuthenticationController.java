package com.example.review.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.review.DTO.AuthDTO;
import com.example.review.DTO.RegisterDTO;
import com.example.review.Services.TokenService;
import com.example.review.entities.User;
import com.example.review.repositories.UserRepository;

import jakarta.validation.Valid;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    @Autowired
    private TokenService service;
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid AuthDTO login) {
        var auth = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(login.username(), login.password()));
        var token = service.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid RegisterDTO register) {
        if(userRepository.findByUsername(register.username()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("Username already exists");
        }

        String encryptedPswd = passwordEncoder.encode(register.password());
        userRepository.save(new User(register.username(), encryptedPswd, register.role()));

        return ResponseEntity.ok().body("User registered successfully");
    }
}