package com.vijay.jpa;

import com.vijay.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepo extends JpaRepository<Transaction,Integer>{
    Optional<List<Transaction>> findByType(String type);
}
