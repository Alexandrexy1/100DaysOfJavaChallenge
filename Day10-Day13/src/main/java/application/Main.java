package application;

import java.util.Arrays;
import java.util.HashSet;

import database.JpaRepository;
import entities.Category;
import entities.Product;
import util.EntityManagerUtil;

public class Main {
	public static void main(String[] args) {		

        JpaRepository<Category> repositoryCategory = new JpaRepository<>(Category.class, "categories");
        JpaRepository<Product> repositoryProduct = new JpaRepository<>(Product.class, "Product");

        JpaRepository<Customer> repositoryCustomer = new JpaRepository<>(Customer.class, "customer");
        
        Customer customer = new Customer("Alex");
        Order order1 = new Order(LocalDateTime.now());
        Order order2 = new Order(LocalDateTime.parse("1986-04-08T12:30:20"));
        Order order3 = new Order(LocalDateTime.parse("2003-05-09T20:10:56"));

        Category category1 = new Category("Books");
        Category category2 = new Category("PC");
        
        Product p1 = new Product("lord of the rings", 20.0);
        Product p2 = new Product("Harry Potter", 20.0);
        Product p3 = new Product("Macbook Air m1", 900.0);
        Product p4 = new Product("PC Gamer Ryzen 5", 550.0);
        
        category1.setProducts(new HashSet<>(Arrays.asList(p1, p2)));
        category2.setProducts(new HashSet<>(Arrays.asList(p3, p4)));
        
        p1.setCategories(new HashSet<>(Arrays.asList(category1)));
        p2.setCategories(new HashSet<>(Arrays.asList(category1)));
        p3.setCategories(new HashSet<>(Arrays.asList(category2)));
        p4.setCategories(new HashSet<>(Arrays.asList(category2)));
        
        repositoryCategory.create(category1);
        repositoryCategory.create(category2);
        repositoryProduct.create(p1);
        repositoryProduct.create(p2);
        repositoryProduct.create(p3);
        repositoryProduct.create(p4);

        repositoryCustomer.create(customer);    
        EntityManagerUtil.close();
        
	}
}
