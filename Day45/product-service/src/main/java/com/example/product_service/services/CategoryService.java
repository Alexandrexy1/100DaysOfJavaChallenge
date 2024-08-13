package com.example.product_service.services;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.product_service.entities.Category;
import com.example.product_service.repositories.CategoryRepository;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public void save(Category category) {
        categoryRepository.save(category);
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findById(Long id) {
        return categoryRepository.findById(id).get();
    }

    public void update(Category entity, Category category) {
        entity.setName(category.getName());
        entity.setProducts(category.getProducts());
    }

    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
