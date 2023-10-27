package com.lms.backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.lms.backend.model.Notification;

public interface NotificationRepository extends MongoRepository<Notification, String> {

}
