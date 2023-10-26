package com.lms.backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.lms.backend.model.User;

public interface UserRepository extends MongoRepository<User, String> {

}
