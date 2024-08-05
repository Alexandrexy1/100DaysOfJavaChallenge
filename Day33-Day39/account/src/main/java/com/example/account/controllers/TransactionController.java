package com.example.account.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.account.Services.TransactionService;
import com.example.account.Services.UserService;
import com.example.account.entities.Transaction;
import com.example.account.entities.User;
import com.example.account.repositories.UserRepository;

@RequestMapping("/transactions")
@RestController
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @Autowired 
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<List<Transaction>> findAll() {
        List<Transaction> transactions = transactionService.findAll();
        // aqui
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> findById(@PathVariable Long id) {
        Transaction transaction = transactionService.findById(id);
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    @PostMapping("/deposit")
    public ResponseEntity<String> deposit(@RequestBody Transaction tra) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) userRepository.findByName(authentication.getName());
        Transaction transaction = new Transaction(tra.getAmount(), tra.getTransactionType(), tra.getDescription(), user);
        userService.deposit(user, transaction);
        return new ResponseEntity<>("Deposit successful.", HttpStatus.OK);
    }
    
    @PostMapping("/withdraw")
    public ResponseEntity<String> withdraw(@RequestBody Transaction tra) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        User user = (User) userRepository.findByName(name);
        Transaction transaction = new Transaction(tra.getAmount(), tra.getTransactionType(), tra.getDescription(), user);
        userService.withdraw(user, transaction);
        return new ResponseEntity<>("Withdrawal successful.", HttpStatus.OK);
    }
}
