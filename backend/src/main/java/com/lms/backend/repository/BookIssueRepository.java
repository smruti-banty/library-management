package com.lms.backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.lms.backend.model.BookIssue;

public interface BookIssueRepository extends MongoRepository<BookIssue, String> {

}
