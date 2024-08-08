package com.example.demo.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.EmployeeDTO;
import com.example.demo.entities.Department;
import com.example.demo.entities.Employee;
import com.example.demo.repositories.EmployeeRepository;

@Service
public class EmployeeService {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    
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

        logger.info("Saving employee: {}", employee);
        employeeRepository.save(employee);
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee findById(Long id) {
        return employeeRepository.findById(id).get();
    }

    public Employee findByName(String name) {
        return employeeRepository.findByName(name);
    }

    public void update(Employee entity, Employee employee) {
        entity.setName(employee.getName());
        entity.setSalary(employee.getSalary());
        entity.setJobTitle(employee.getJobTitle());
        Department department = departmentService.findById(employee.getDepartment().getId());
        entity.setDepartment(department);
        logger.info("updated Employee: {}", entity);
    }
    
    public void deleteById(Long id) {
        Employee employee = employeeRepository.findById(id).get();
        logger.info("deleted employee: {}", employee);
        employeeRepository.deleteById(id);
    }

}
