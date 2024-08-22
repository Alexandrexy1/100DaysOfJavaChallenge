package com.example.product_service.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.product_service.entities.Product;
import com.example.product_service.repositories.ProductRepository;
import com.example.product_service.producers.ProductProducer;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductProducer productProducer;

    @Transactional
    public void save(Product product) {
        Product savedProduct = productRepository.save(product);
        productProducer.publishMessageProduct(savedProduct);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        return productRepository.findById(id).get();
    }

    public void update(Product entity, Product product) {
        entity.setName(product.getName());
        entity.setCategory(product.getCategory());
        entity.setDescription(product.getDescription());
        entity.setPrice(product.getPrice());
        entity.setStockQuantity(product.getStockQuantity());
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
