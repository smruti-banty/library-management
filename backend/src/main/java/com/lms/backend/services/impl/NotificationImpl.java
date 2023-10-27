package com.lms.backend.services.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lms.backend.constants.NotificationStateStatus;
import com.lms.backend.constants.NotificationStatus;
import com.lms.backend.model.Notification;
import com.lms.backend.repository.NotificationRepository;
import com.lms.backend.services.NotificationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    @Override
    public Notification sendNotification(Notification notification) {
        notificationRepository.save(notification);
        return notification;
    }

    @Override
    public void markAsRead() {
        List<Notification> allNotifications = notificationRepository.findAll();
        allNotifications.forEach(notification -> {
            notification.setStatus(NotificationStatus.READ);
            notificationRepository.save(notification);
        });
    }

    @Override
    public List<Notification> getAllNotification() {
        return notificationRepository.findAllByStatus(NotificationStateStatus.ACTIVE);
    }

    @Override
    public Notification deleteNotification(String id) {
        var oldNotification = notificationRepository.findById(id).orElseThrow();

        oldNotification.setState(NotificationStateStatus.DEACTIVE);
        oldNotification.setUpdatedDate(LocalDateTime.now());

        notificationRepository.save(oldNotification);
        return oldNotification;
    }

}
