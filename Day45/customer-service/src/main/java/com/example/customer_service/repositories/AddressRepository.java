package com.example.customer_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.customer_service.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
    
}
