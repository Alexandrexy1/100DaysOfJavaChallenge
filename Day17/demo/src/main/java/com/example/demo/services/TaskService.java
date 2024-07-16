package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Task;
import com.example.demo.repositories.TaskRepository;


@Service
public class TaskService {
	@Autowired
	private TaskRepository repository;

    public List<Task> findAll() {
		return repository.findAll();
	}
	
	public Task findById(Long id) {
		Optional<Task> object = repository.findById(id);
		
		return object.orElseThrow(() -> new IllegalArgumentException("id doesn't exists. Id " + id));
	}
	
	public Task insert(Task Task) {
		return repository.save(Task);
	}

	public void delete(Long id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);				
        } else throw new IllegalArgumentException("Id doesn't exists.");
	}

	public Task update(Long id, Task obj) {
        Task entity = repository.getReferenceById(id);
        updateTask(entity, obj);
        return repository.save(entity);
	}

	private void updateTask(Task entity, Task obj) {
		entity.setName(obj.getName());
		entity.setDescription(obj.getDescription());
	}
}
