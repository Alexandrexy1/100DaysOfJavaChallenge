package application;

import java.util.ArrayList;
import java.util.List;

import entities.Product;

public class Main {
    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();

        products.add(new Product("iPhone 14", 4999.90));
        products.add(new Product("Samsung Galaxy Book4", 3779.10));
        products.add(new Product("Samsung S23", 2800.00));

        products.sort((product1, product2) -> product2.getPrice().compareTo(product1.getPrice()));

        for (Product product: products) {
            System.out.println(product);
            // name: iPhone 14 price: R$ 4999,90
            // name: Samsung Galaxy Book4 price: R$ 3779,10
            // name: Samsung S23 price: R$ 2800,00
        }
    }
}
