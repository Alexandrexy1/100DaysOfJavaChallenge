package com.example.account.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.account.entities.enums.TransactionType;
import com.example.account.entities.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_user")
public class User implements UserDetails {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String email;

    @JsonIgnore
    private String password;

    public UserRole role;
    
    private BigDecimal balance = BigDecimal.ZERO;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions = new ArrayList<>();

    public User() {}

    public User(String name, String email, String password, UserRole role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }
    
    public void deposit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) throw new IllegalArgumentException("Deposit amount must be positive.");
        balance = balance.add(amount);
        transactions.add(new Transaction(amount, TransactionType.DEPOSIT, "deposit successful", this));
    }

    public void withdraw(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) throw new IllegalArgumentException("Withdraw amount must be positive.");
        if (amount.compareTo(balance) > 0) throw new IllegalArgumentException("Insufficient funds.");
        balance = balance.subtract(amount);
        transactions.add(new Transaction(amount, TransactionType.WITHDRAWAL, "withdrawal successful", this)); 
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Transaction> geTransactions() {
        return transactions;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role == UserRole.USER ? 
            List.of(new SimpleGrantedAuthority("ROLE_USER"), new SimpleGrantedAuthority("ROLE_GUEST")) :
            List.of(new SimpleGrantedAuthority("ROLE_GUEST"));
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
