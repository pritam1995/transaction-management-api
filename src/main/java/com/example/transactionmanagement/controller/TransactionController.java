package com.example.transactionmanagement.controller;

import com.example.transactionmanagement.model.Category;
import com.example.transactionmanagement.model.Transaction;
import com.example.transactionmanagement.service.CategoryService;
import com.example.transactionmanagement.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransactionController {

    private final TransactionService transactionService;
    private final CategoryService categoryService;

    @Autowired
    public TransactionController(TransactionService transactionService, CategoryService categoryService) {
        this.transactionService = transactionService;
        this.categoryService = categoryService;
    }

    @PostMapping(value = "/transaction/{amount}/{category}/{description}")
    public Transaction addTransaction(@PathVariable double amount, @PathVariable String category, @PathVariable String description){
        Transaction transaction = new Transaction();
        Category getCategory = categoryService.getParticularCategory(category);
        transaction.setCategory(getCategory);
        transaction.setAmount(amount);
        transaction.setDescription(description);
        return transactionService.addDetailedTransaction(transaction);
    }

    @GetMapping(value = "/transaction/{id}")
    public Transaction getTransaction(@PathVariable int id){
        return transactionService.getParticularTransaction(id);
    }
}
