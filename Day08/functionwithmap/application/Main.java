package application;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import entities.Product;
import services.ProductService;

public class Main {
    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();
        ProductService productService = new ProductService();

        products.add(new Product("iPhone 13", 2900));
        products.add(new Product("Asus Vivobook 16", 3484));
        products.add(new Product("Acer Aspire 5", 2500));
        
        List<String> nameProducts = products.stream().map(product -> product.getName().toUpperCase()).collect(Collectors.toList());
        nameProducts.forEach(System.out::println);
        // IPHONE 13
        // ASUS VIVOBOOK 16
        // ACER ASPIRE 5

        String total = productService.sumFrom(products, p -> p.getPrice() > 2600);
        System.out.println(total);
        // total: 6384.0
    }
}