package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void save(User user) {
        System.out.println("cheguei no userService save");
        userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public void update(User entity, User user) {
        entity.setUsername(user.getUsername());
        entity.setPassword(user.getPassword());

    }
}
