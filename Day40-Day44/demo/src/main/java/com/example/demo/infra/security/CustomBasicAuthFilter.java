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
        String path = request.getServletPath();

        if("/users".equals(path) && request.getMethod().equals("POST")) {
            filterChain.doFilter(request, response);
            return;
        }

        var basicAuth = isBasicAuth(request);

        if (basicAuth != null) {
            String codeBase64 = basicAuth;
            String[] decodeBase64 = decode(codeBase64).split(":");
            String username = decodeBase64[0];
            String password = decodeBase64[1];

            User user = userRepository.findByUsername(username);
            boolean isValid = validPassword(password, user.getPassword());

            if (!isValid) response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            setAuth(user);
        }
        filterChain.doFilter(request, response);
    }
    
    private void setAuth(User user) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String decode(String base) {
        return new String(Base64.getDecoder().decode(base));
    }

    private boolean validPassword(String password, String userPassword) {
        return passwordEncoder().matches(password, userPassword);
    }

    private String isBasicAuth(HttpServletRequest request) {
        var header = request.getHeader("Authorization");
        if (header != null) {
            return header.replace("Basic ", "");
        }
        return null;  
    }
    
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}