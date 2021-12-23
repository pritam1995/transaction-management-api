package com.example.transactionmanagement.service;

import com.example.transactionmanagement.model.Category;
import com.example.transactionmanagement.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category createCategory(Category category){
        return categoryRepository.save(category);
    }

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    public Category getParticularCategory(String category){
        return categoryRepository.getById(category);
    }

    public void deleteCategory(String category){
        categoryRepository.deleteById(category);
    }
}
