package com.example.review.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.review.entities.User;
import com.example.review.repositories.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(Long id) {
        return repository.findById(id).get();
    }

    public void save(User user) {
        if(usernameExists(user.getUsername())) throw new IllegalArgumentException("Name already exists");
        repository.save(user);
    }

    public Boolean usernameExists(String name) {
        return repository.findByUsername(name).isPresent();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public void update(User entity, User user) {
        entity.setUsername(user.getUsername());
        entity.setPassword(user.getPassword());
    }
}
