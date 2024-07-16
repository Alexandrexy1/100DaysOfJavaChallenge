package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.User;
import com.example.demo.exceptions.InvalidEmailException;
import com.example.demo.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;

	public void emailValidation(User user) {
		if(user.getEmail() == null || !user.getEmail().contains("@")) {
			throw new InvalidEmailException("Email cannot be null", user.getEmail());
		}
	}
	
	public List<User> findAll() {
		return repository.findAll();
	}
	
	public User findById(Long id) {
		Optional<User> object = repository.findById(id);
		
		return object.orElseThrow(() -> new IllegalArgumentException("id doesn't exists. Id " + id));
	}
	
	public User insert(User user) {
		emailValidation(user);
		return repository.save(user);
	}

	public void delete(Long id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);				
        } else throw new IllegalArgumentException("Id doesn't exists.");
	}

	public User update(Long id, User obj) {
        User entity = repository.getReferenceById(id);
        updateUser(entity, obj);
        return repository.save(entity);
	}

	private void updateUser(User entity, User obj) {
		entity.setEmail(obj.getEmail());
		entity.setPassword(obj.getPassword());
	}
}
