package com.lms.backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.lms.backend.model.Book;

public interface BookRepository extends MongoRepository<Book, String>{
    
}
