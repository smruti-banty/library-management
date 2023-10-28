package com.lms.backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.lms.backend.model.Batch;

public interface BatchRepository extends MongoRepository<Batch, String>{
    
}
