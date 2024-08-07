package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo.services.EmployeeService;
import com.example.demo.DTO.EmployeeDTO;
import com.example.demo.entities.Department;
import com.example.demo.entities.Employee;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class DemoApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Autowired
	EmployeeService employeeService;

	@Test
	public void contextLoads() {
		String baseUrl = "http://localhost:" + port;

		Department department1 = new Department("Tech");
		Department department2 = new Department("Analogy");
		
		ResponseEntity<Department> departmentResponse = testRestTemplate.postForEntity(baseUrl + "/departments", department1, Department.class);
		assertEquals(HttpStatus.CREATED, departmentResponse.getStatusCode());

		ResponseEntity<Department> departmentResponse2 = testRestTemplate.postForEntity(baseUrl + "/departments", department2, Department.class);
		assertEquals(HttpStatus.CREATED, departmentResponse2.getStatusCode());
		
		EmployeeDTO employeeSaved = new EmployeeDTO("Alex", "FullStack Developer", departmentResponse.getBody().getId(), 3200.);
		ResponseEntity<String> employeeResponse = testRestTemplate.postForEntity(baseUrl +"/employees", employeeSaved, String.class);
		assertEquals(HttpStatus.CREATED, employeeResponse.getStatusCode());
		
		
		EmployeeDTO employeeUpdate = new EmployeeDTO("Alex", "FullStack Developer", departmentResponse.getBody().getId(), 4800.);
		
		Employee employee = employeeService.findByName(employeeUpdate.getName());
		employee.setDepartment(departmentResponse2.getBody());

		HttpHeaders headers = new HttpHeaders();
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Employee> requestUpdate = new HttpEntity<>(employee, headers);
		ResponseEntity<Employee> employeeUpdResponse = testRestTemplate.exchange(baseUrl +"/employees/" + employee.getId(), HttpMethod.PUT, requestUpdate, Employee.class);
		assertEquals(HttpStatus.OK, employeeUpdResponse.getStatusCode());
	}
}
