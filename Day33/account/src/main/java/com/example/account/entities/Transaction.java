package com.example.account.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.account.entities.enums.TransactionStatus;
import com.example.account.entities.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private TransactionType transactionType;
    private TransactionStatus status;

    private String description;
    private BigDecimal amount;
    private LocalDateTime date = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "id_user")
    @JsonIgnoreProperties("balance")
    private User user;
    
    public Transaction() {}

    public Transaction(BigDecimal amount, TransactionType transactionType, String description, User user) {
        this.transactionType = transactionType;
        this.description = description;
        this.amount = amount;
        this.user = user;
        this.status = TransactionStatus.PENDING;
    }

    public Long getId() {
        return id;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public User getUser() {
        return user;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }
}
