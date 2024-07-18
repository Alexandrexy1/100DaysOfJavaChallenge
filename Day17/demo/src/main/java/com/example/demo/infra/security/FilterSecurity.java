package com.example.demo.infra.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.repositories.UserRepository;
import com.example.demo.services.TokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public final class FilterSecurity extends OncePerRequestFilter {

    @Autowired
    UserRepository repository;

    @Autowired
    TokenService service;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        var token = retrieveToken(request);
        if (token != null) {
            var email = service.validate(token);
            UserDetails user = repository.findByEmail(email);

            var auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        
        filterChain.doFilter(request, response);
    }

    protected String retrieveToken(HttpServletRequest request) {
        var header = request.getHeader("Authorization");
        if (!(header == null)) return header.replace("Bearer ", "");
        return null;
    }   
    
}
