package com.example.demo.infra.security;

import java.io.IOException;
import java.util.Base64;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomBasicAuthFilter extends OncePerRequestFilter {
    @Autowired
    UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (isBasicAuth(request)) {
            String codeBase64 = request.getHeader("Authorization").replace("Basic ", "");
            String[] decodeBase64 = decode(codeBase64).split(":");

            String username = decodeBase64[0];
            String password = decodeBase64[1];

            User user = userRepository.findByUsername(username);
            System.out.println("username: " + user.getUsername());
            System.out.println("username: " + user.getUsername());
            System.out.println("username: " + user.getUsername());
            System.out.println("username: " + user.getUsername());
            System.out.println("OIIIII");

            boolean isValid = validPassword(password, user.getPassword());

            if (!isValid) response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            setAuth(user);
        }
    }
    
    private void setAuth(User user) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String decode(String base) {
        return Base64.getDecoder().decode(base).toString();
    }

    private boolean validPassword(String password, String userPassword) {
        return passwordEncoder().matches(password, userPassword);
    }

    private boolean isBasicAuth(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        return header != null && header.startsWith("Basic ");
    }
    
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
