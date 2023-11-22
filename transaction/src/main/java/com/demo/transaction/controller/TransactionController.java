package com.demo.transaction.controller;

import com.demo.transaction.entity.Transaction;
import com.demo.transaction.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping
    public List<Transaction> getAll(){
        return transactionRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Transaction> save(@RequestBody Transaction transaction) {
        transactionRepository.save(transaction);
        return ResponseEntity.ok(transaction);
    }

    @PutMapping
    public ResponseEntity<Transaction> update(@RequestBody Transaction transaction) {
        transactionRepository.save(transaction);
        return ResponseEntity.ok(transaction);
    }

    @GetMapping("/findByaccountIban/{accountIban}")
    public List<Transaction> findByaccountIban(@PathVariable String accountIban) {
        return transactionRepository.findByaccountIban(accountIban);
    }
}
