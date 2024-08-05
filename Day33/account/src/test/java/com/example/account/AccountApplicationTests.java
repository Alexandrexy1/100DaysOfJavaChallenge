package com.example.account;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.example.account.DTO.TransactionDTO;
import com.example.account.DTO.UserDTO;
import com.example.account.entities.enums.TransactionType;
import com.example.account.entities.enums.UserRole;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class AccountApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testUserRegistrationAndTransactionCreation() {
        String baseUrl = "http://localhost:" + port;
        UserDTO registerUser = new UserDTO("Alex", "alex@test.com", "12345", UserRole.USER);
        ResponseEntity<String> registerResponse = restTemplate.postForEntity(baseUrl + "/auth/register", registerUser, String.class);
        assertEquals(HttpStatus.CREATED, registerResponse.getStatusCode());

        UserDTO loginUser = new UserDTO("Alex", "alex@test.com", "12345", UserRole.USER);

        ResponseEntity<String> loginResponse = restTemplate.postForEntity(baseUrl + "/auth/login", loginUser, String.class);

        assertEquals(HttpStatus.OK, loginResponse.getStatusCode());
        String jwtToken = loginResponse.getBody();
    
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(jwtToken);

        
        TransactionDTO transactionDeposity = new TransactionDTO(new BigDecimal(200), TransactionType.DEPOSIT, "", loginUser);
        HttpEntity<TransactionDTO> entityDeposity = new HttpEntity<>(transactionDeposity, headers);
        ResponseEntity<String> transactionRepDeposity = restTemplate.postForEntity(baseUrl + "/transactions/deposit", entityDeposity, String.class);
        
        TransactionDTO transactionWithdrawal = new TransactionDTO(new BigDecimal(200), TransactionType.WITHDRAWAL, "", loginUser);
        HttpEntity<TransactionDTO> entityWithdrawal = new HttpEntity<>(transactionWithdrawal, headers);
        ResponseEntity<String> transactionRepWithdrawalResponseEntity = restTemplate.postForEntity(baseUrl + "/transactions/withdraw", entityWithdrawal, String.class);
        
        System.out.println("#########");
        System.out.println("Deposit: " + transactionRepDeposity.getStatusCode());        
        System.out.println("Withdrawal: " + transactionRepWithdrawalResponseEntity.getStatusCode());        

    }
}
