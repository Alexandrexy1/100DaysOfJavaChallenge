package com.example.account.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.account.Services.TokenService;
import com.example.account.dto.LoginDTO;
import com.example.account.dto.RegisterDTO;
import com.example.account.entities.User;
import com.example.account.repositories.UserRepository;

import jakarta.validation.Valid;

@RequestMapping("/auth")
@RestController
public class AuthController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenService tokenService;

    @Autowired
    AuthenticationManager authenticationManager;


    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterDTO register) {
        if(userRepository.findByName(register.name()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(register.password());
        User user = new User(register.name(), register.email(), encryptedPassword, register.role());
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginDTO login) {
        var auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.name(), login.password()));
        var token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(token);
    }
}
