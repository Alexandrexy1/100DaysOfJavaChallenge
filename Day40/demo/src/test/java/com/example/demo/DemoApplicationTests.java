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

import com.example.demo.services.DepartmentService;
import com.example.demo.services.EmployeeService;
import com.example.demo.DTO.EmployeeDTO;
import com.example.demo.entities.Department;
import com.example.demo.entities.Employee;
import com.example.demo.entities.User;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class DemoApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Autowired
	EmployeeService employeeService;

	@Autowired
	DepartmentService departmentService;

	private String baseUrl;

	@Test
	public void contextLoads() {
		baseUrl = "http://localhost:" + port;

		User user = createUser("Alex", "12345");

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setBasicAuth(user.getUsername(), user.getPassword());

	
		Department department1 = createDepartment("Tech");
		Department department2 = createDepartment("Industrial");

		System.out.println("department name:" + department1.getDepartmentName());
		System.out.println("department name:" + department2.getDepartmentName());
		
		// EmployeeDTO employeeSaved = new EmployeeDTO("Maria", "FullStack Developer", department1.getId(), 3200.);
		// createEmployee(employeeSaved);
		
		// EmployeeDTO employeeUpdate = new EmployeeDTO("Maria", "FullStack Developer", department2.getId(), 4800.);
		// updateEmployee(employeeUpdate);
	}

	private User createUser(String name, String password) {
		User user = new User(name, password);
		ResponseEntity<User> userResponse = testRestTemplate.postForEntity(baseUrl + "/users", user, User.class);
		assertEquals(HttpStatus.CREATED, userResponse.getStatusCode());
		return userResponse.getBody();
	}

	private Department createDepartment(String name) {
		Department department = new Department(name);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Department> requestEntity = new HttpEntity<>(department, headers);

		ResponseEntity<Department> departmentResponse = testRestTemplate.exchange(baseUrl + "/departments", HttpMethod.POST, requestEntity, Department.class);
		assertEquals(HttpStatus.CREATED, departmentResponse);
		return departmentResponse.getBody();
	}

	private void createEmployee(EmployeeDTO employee) {
		ResponseEntity<String> employeeResponse = testRestTemplate.postForEntity(baseUrl + "/employees", employee, String.class);
		assertEquals(HttpStatus.CREATED, employeeResponse);
	}

	private void updateEmployee(EmployeeDTO employee) {
		Employee employeeUpdate = employeeService.findByName(employee.getName());
		employeeUpdate.setDepartment(departmentService.findById(employee.getDepartmentId()));
		
		HttpHeaders headers = new HttpHeaders();
			
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Employee> requestUpdate = new HttpEntity<>(employeeUpdate, headers);
		ResponseEntity<Employee> employeeUpdResponse = testRestTemplate.exchange(baseUrl +"/employees/" + employeeUpdate.getId(), HttpMethod.PUT, requestUpdate, Employee.class);
		assertEquals(HttpStatus.OK, employeeUpdResponse.getStatusCode());
	}

}

