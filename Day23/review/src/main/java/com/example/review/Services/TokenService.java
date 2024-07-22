package com.example.review.Services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.example.review.entities.User;


@Service
public class TokenService {
    
    @Value("${api.security.token.secret.key}")
    private String secretKey;

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            String token = JWT.create().withIssuer("authentication-key")
                .withSubject(user.getUsername())
                .withExpiresAt(expiresAtHours())
                .sign(algorithm);
            return token;
        } catch(JWTCreationException e) {
            throw new JWTCreationException(e.getMessage(), e.getCause());
        }
    }

    public String tokenValidation(String token) {
        try {
             Algorithm algorithm = Algorithm.HMAC256(token);
             String require = JWT.require(algorithm)
                    .withIssuer("authentication-key")
                    .build()
                    .verify(token)
                    .getSubject();
            return require;
        } catch(JWTCreationException e) {
            throw new JWTCreationException(e.getMessage(), e.getCause());
        }
    }
 
    public Instant expiresAtHours() {
        return LocalDateTime.now().plusHours(3).toInstant(ZoneOffset.ofHours(-3));
    }

}
