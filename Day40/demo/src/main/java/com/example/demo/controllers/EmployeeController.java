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

import com.example.demo.DTO.EmployeeDTO;
import com.example.demo.entities.Employee;
import com.example.demo.services.EmployeeService;

@RequestMapping("/employees")
@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<Employee>> findAll() {
        List<Employee> employees = employeeService.findAll();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> findById(@PathVariable Long id) {
        Employee employee = employeeService.findById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<EmployeeDTO> save(@RequestBody EmployeeDTO entity) {
        employeeService.save(entity);
        return new ResponseEntity<>(entity, HttpStatus.CREATED);
    }
}
