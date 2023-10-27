package com.lms.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.lms.backend.constants.UserRole;
import com.lms.backend.model.User;

public interface UserRepository extends MongoRepository<User, String> {
    List<User> findAllByUserRole(UserRole userRole);

    Optional<User> findByReferenceNumber(String referenceNumber);
}
