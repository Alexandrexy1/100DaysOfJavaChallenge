package application;

import java.util.HashSet;
import java.util.Set;

import entities.Product;

public class Main {
    public static void main(String[] args) {
        Set<Product> products = new HashSet<>();
        
        products.add(new Product("TV Sony", 1600.));
        products.add(new Product("iPhone 15 pro max", 7000.));
        products.add(new Product("Geladeira electrolux", 2100.));
        products.add(new Product("Armário", 800.));

        products.removeIf(product -> product.getPrice() < 2000);
        
        for(Product product: products) {
            System.out.println(product);
        } 
    }
}