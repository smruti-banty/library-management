package com.lms.backend.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.lms.backend.constants.NotificationStateStatus;
import com.lms.backend.model.Notification;

public interface NotificationRepository extends MongoRepository<Notification, String> {
    List<Notification> findAllByStatus(NotificationStateStatus stateStatus);

    List<Notification> findByReciverUserId(String reciverUserId);
}
