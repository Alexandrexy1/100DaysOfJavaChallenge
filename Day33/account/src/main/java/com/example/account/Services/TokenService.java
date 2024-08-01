package com.example.account.Services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.example.account.entities.User;

@Service
public class TokenService {
    @Value("${api.security.token.secret.key}")
    private String key;

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(key);
            String token = JWT.create().withIssuer("auth-key").withSubject(user.getName()).withExpiresAt(expiresAt()).sign(algorithm);
            return token;
        } catch(JWTCreationException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    public String validation(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(key);
            return JWT.require(algorithm).withIssuer("auth-key").build().verify(token).getSubject();
        } catch(JWTCreationException e) {
            throw new RuntimeException("Error:" + e.getMessage());
        }
    }

    public Instant expiresAt() {
        return LocalDateTime.now().plusMinutes(3).toInstant(ZoneOffset.ofHours(-3));
    }
}

