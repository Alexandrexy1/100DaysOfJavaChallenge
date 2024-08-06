package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.EmployeeDTO;
import com.example.demo.entities.Employee;
import com.example.demo.repositories.EmployeeRepository;

@Service
public class EmployeeService {
    
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentService departmentService;

    public void save(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setDepartment(departmentService.findById(employeeDTO.getDepartmentId()));
        employee.setJobTitle(employeeDTO.getJobTitle());
        employee.setSalary(employeeDTO.getSalary());
        employeeRepository.save(employee);
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee findById(Long id) {
        return employeeRepository.findById(id).get();
    }

    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }

    public void update(Employee entity, Employee employee) {
        entity.setName(employee.getName());
        entity.setSalary(employee.getSalary());
        entity.setJobTitle(employee.getJobTitle());
        entity.setDepartment(employee.getDepartment());
    }
}
