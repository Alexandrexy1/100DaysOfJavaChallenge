package com.example.customer_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.customer_service.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
    
}
