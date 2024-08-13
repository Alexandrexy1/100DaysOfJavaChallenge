package com.example.product_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.product_service.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    
}
