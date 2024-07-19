package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    public UserDetails findByEmail(String email);
}
