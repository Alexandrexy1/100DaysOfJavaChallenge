package services;

import java.util.List;
import java.util.function.Predicate;

import entities.Product;

public class ProductService {
    public String sumFrom(List<Product> products, Predicate<Product> predicate) {
        double total = 0;
        for(Product product: products) {
            if (predicate.test(product)) {
                total += product.getPrice();
            }
        }
        return "total: " + String.format("%.2f", total);
    }
}
