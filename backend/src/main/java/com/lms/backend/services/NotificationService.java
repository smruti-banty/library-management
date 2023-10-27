package com.lms.backend.services;

import java.util.List;

import com.lms.backend.model.Notification;

public interface NotificationService {
    Notification sendNotification(Notification notification);

    void markAsRead();

    List<Notification> getAllNotification();

    Notification deleteNotification(String id);
}
