package com.example.demo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.User;
import com.example.demo.entities.dto.AuthDTO;
import com.example.demo.entities.dto.RegisterDTO;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.TokenService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RequestMapping("/auth")
@RestController
public class AuthController {

    @Autowired
    private TokenService service;
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid AuthDTO login) {
        var auth = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(login.email(), login.password()));

        var token = service.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterDTO register) {
        if(userRepository.findByEmail(register.email()) != null) return ResponseEntity.badRequest().build();

        String encryptedPswd = new BCryptPasswordEncoder().encode(register.password());
        userRepository.save(new User(register.email(), encryptedPswd, register.role()));

        return ResponseEntity.ok().build();
    }
}
