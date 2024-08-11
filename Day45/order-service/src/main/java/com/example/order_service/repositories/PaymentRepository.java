package com.example.order_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.order_service.entities.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    
}
