package com.example.account.DTO;

import java.math.BigDecimal;

import com.example.account.entities.enums.TransactionType;

public record TransactionDTO(BigDecimal amount, TransactionType transactionType, String description, UserDTO user) {

}
