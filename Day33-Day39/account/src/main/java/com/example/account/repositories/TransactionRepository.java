package com.example.account.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.account.entities.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    
}
