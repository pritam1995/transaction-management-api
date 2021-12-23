package com.example.transactionmanagement.controller;

import com.example.transactionmanagement.model.Category;
import com.example.transactionmanagement.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping(value = "/category/create")
    public Category addCategory(@RequestBody Category category){
        return categoryService.createCategory(category);
    }


    @GetMapping(value = "/category/{id}")
    public Category getCategoryById(@PathVariable String id){
        return categoryService.getParticularCategory(id);
    }
}
