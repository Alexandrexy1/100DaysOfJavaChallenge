package com.example.customer_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.customer_service.entities.Adress;

public interface AdressRepository extends JpaRepository<Adress, Long> {
    
}
