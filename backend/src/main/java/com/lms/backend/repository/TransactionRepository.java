package com.lms.backend.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.lms.backend.model.Transaction;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
    Transaction findByBookIssueId(String bookIssueId);

    List<Transaction> findTop5ByCreatedDateBeforeOrderByCreatedDateDesc(LocalDateTime currentDateTime);
}
