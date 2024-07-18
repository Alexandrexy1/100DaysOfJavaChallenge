package com.example.demo.services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.example.demo.entities.User;

@Service
public class TokenService {
    @Value("${api.security.token.secret.key}")
    private String secretKey;

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            String token = JWT.create().withIssuer("auth-key").withSubject(user.getEmail()).withExpiresAt(expiresData()).sign(algorithm);
            return token;

        } catch (JWTCreationException e) {
            throw new JWTCreationException(e.getMessage(), e.getCause());
        }
    }

    public String validate(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);

            return JWT.require(algorithm).withIssuer("auth-key").build().verify(token).getSubject();
        } catch (JWTCreationException e) {
            return "Invalid Token";
        } 
    }

    private Instant expiresData() {
        return LocalDateTime.now().plusHours(3).toInstant(ZoneOffset.ofHours(-3));
    }
}
