package com.example.review.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.review.entities.Product;
import com.example.review.repositories.ProductRepository;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository repository;

    public List<Product> findAll() {
        return repository.findAll();
    }

    public Product findById(Long id) {
        return repository.findById(id).get();
    }

    public void save(Product product) {
        repository.save(product);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
