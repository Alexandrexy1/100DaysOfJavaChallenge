package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo.services.DepartmentService;
import com.example.demo.DTO.EmployeeDTO;
import com.example.demo.entities.Department;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class DemoApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Autowired
	DepartmentService service;

	@Test
	public void contextLoads() {
		String baseUrl = "http://localhost:" + port;
		Department department = new Department("Tech");

		ResponseEntity<Department> departmentResponse = testRestTemplate.postForEntity(baseUrl + "/departments", department, Department.class);
		assertEquals(HttpStatus.CREATED, departmentResponse.getStatusCode());
		
		EmployeeDTO employee = new EmployeeDTO("Alex", "FullStack Developer", departmentResponse.getBody().getId(), 3200.);
		ResponseEntity<String> employeeResponse = testRestTemplate.postForEntity(baseUrl +"/employees", employee, String.class);
		assertEquals(HttpStatus.CREATED, employeeResponse.getStatusCode());
	}
}
