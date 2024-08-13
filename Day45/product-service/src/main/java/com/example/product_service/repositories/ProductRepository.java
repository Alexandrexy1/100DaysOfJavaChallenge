package com.example.product_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.product_service.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
