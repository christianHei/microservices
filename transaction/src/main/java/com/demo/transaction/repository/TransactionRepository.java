package com.demo.transaction.repository;

import com.demo.transaction.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("select t from Transaction t where t.accountIban = :accountIban")
    public List<Transaction> findByaccountIban(String accountIban);
}
