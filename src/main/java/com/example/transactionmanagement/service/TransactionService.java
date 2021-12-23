package com.example.transactionmanagement.service;

import com.example.transactionmanagement.model.Transaction;
import com.example.transactionmanagement.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction getParticularTransaction(int id) {
        return transactionRepository.getById(id);
    }

    public Transaction addDetailedTransaction(Transaction transaction){
        return transactionRepository.save(transaction);
    }

    public Transaction updateParticularTransaction(Transaction transaction, int id){
        Transaction oldTransaction = transactionRepository.getById(id);
        oldTransaction.setDescription(transaction.getDescription());
        oldTransaction.setAmount(transaction.getAmount());
        return transactionRepository.save(oldTransaction);
    }
}
