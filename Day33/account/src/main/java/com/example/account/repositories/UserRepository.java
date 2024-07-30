package com.example.account.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.account.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
