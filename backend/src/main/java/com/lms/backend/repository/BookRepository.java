package com.lms.backend.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.lms.backend.constants.BookStatus;
import com.lms.backend.model.Book;

public interface BookRepository extends MongoRepository<Book, String> {
    List<Book> findAllByStatus(BookStatus status);
}
