package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Department;
import com.example.demo.repositories.DepartmentRepository;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;
 
    public void save(Department entity) {
        departmentRepository.save(entity);
    }

    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    public Department findById(Long id) {
        return departmentRepository.findById(id).get();
    }

    public void deleteById(Long id) {
        departmentRepository.deleteById(id);
    }

    public void update(Department entity, Department department) {
        entity.setDepartmentName(department.getDepartmentName());
    
    }
}
