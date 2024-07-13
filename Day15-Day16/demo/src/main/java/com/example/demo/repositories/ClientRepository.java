package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Client;


public interface ClientRepository extends JpaRepository<Client, Long> {
    
}
