package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Department;
import com.example.demo.services.DepartmentService;


@RequestMapping("/departments")
@RestController
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<List<Department>> findAll() {
        List<Department> departments = departmentService.findAll();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> findById(@PathVariable Long id) {
        Department department = departmentService.findById(id);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Department> save(@RequestBody Department entity) {
        Department savedDepartment = departmentService.save(entity);
        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
    }
}
