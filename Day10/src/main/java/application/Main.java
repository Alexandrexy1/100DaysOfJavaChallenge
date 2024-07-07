package application;

import java.util.HashMap;
import java.util.Map;

import entities.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
	public static void main(String[] args) {
        String jdbcUrl = System.getenv("jdbc_url");
        String jdbcUsername = System.getenv("jdbc_user");
        String jdbcPassword = System.getenv("jdbc_password");
        
        Map<String, String> properties = new HashMap<>();
  
        properties.put("jakarta.persistence.jdbc.url", jdbcUrl);
        properties.put("jakarta.persistence.jdbc.user", jdbcUsername);
        properties.put("jakarta.persistence.jdbc.password", jdbcPassword);
        
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("testdb", properties);
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
        Person person = new Person("Alexandre", 20);
        em.persist(person);
        
        em.getTransaction().commit();
        
        em.close();
        emf.close();
	}
}
