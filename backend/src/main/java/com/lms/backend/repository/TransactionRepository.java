package com.lms.backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.lms.backend.model.Transaction;

public interface TransactionRepository extends MongoRepository<Transaction, String> {

}
