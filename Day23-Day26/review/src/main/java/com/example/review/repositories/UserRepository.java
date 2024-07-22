package com.example.review.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.review.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    public UserDetails findByUsername(String username);
}
