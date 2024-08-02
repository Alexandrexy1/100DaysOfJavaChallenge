package com.example.account.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.account.entities.Transaction;
import com.example.account.entities.User;
import com.example.account.entities.enums.TransactionStatus;
import com.example.account.repositories.TransactionRepository;
import com.example.account.repositories.UserRepository;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired UserRepository userRepository;

    public List<Transaction> findAll() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) userRepository.findByName(authentication.getName());
        return user.getTransactions();
    }

    public Transaction findById(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) userRepository.findByName(authentication.getName());
        for (Transaction transaction : user.getTransactions()) {
            if (transaction.getId() == id) return transaction;
        }
        return null;
    }

    public void save(Transaction transaction) {
        transaction.setStatus(TransactionStatus.COMPLETED);
        transactionRepository.save(transaction);
    }
}
