package com.example.review.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.review.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
