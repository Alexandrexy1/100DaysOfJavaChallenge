package application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import database.JpaRepository;
import entities.Customer;
import entities.Order;
import util.EntityManagerUtil;

public class Main {
	public static void main(String[] args) {		
        JpaRepository<Customer> repositoryCustomer = new JpaRepository<>(Customer.class, "customer");
        
        Customer customer = new Customer("Alex");
        Order order1 = new Order(LocalDateTime.now());
        Order order2 = new Order(LocalDateTime.parse("1986-04-08T12:30:20"));
        Order order3 = new Order(LocalDateTime.parse("2003-05-09T20:10:56"));

        List<Order> orders = Arrays.asList(order1, order2, order3);
        
        customer.setOrders(orders);
        order1.setCustomer(customer);
        order2.setCustomer(customer);
        order3.setCustomer(customer);
        
        repositoryCustomer.create(customer);
        
        EntityManagerUtil.close();
        
	}
}
