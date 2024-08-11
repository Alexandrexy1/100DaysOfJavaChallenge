package com.example.order_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.order_service.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    
}
