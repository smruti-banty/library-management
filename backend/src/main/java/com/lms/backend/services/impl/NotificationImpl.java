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
    public void markAsRead(String receiverId) {
        var notifications = notificationRepository.findByReceiverUserIdAndStatus(receiverId, NotificationStatus.UNREAD);

        var lists = notifications.stream().map(notification -> {
            notification.setStatus(NotificationStatus.READ);
            return notification;
        }).toList();

        notificationRepository.saveAll(lists);
    }

    @Override
    public List<Notification> getAllNotification() {
        return notificationRepository.findAllByStatus(NotificationStateStatus.ACTIVE);
    }

    @Override
    public Notification deleteNotification(String notificationId) {
        var oldNotification = notificationRepository.findById(notificationId).orElseThrow();

        oldNotification.setState(NotificationStateStatus.INACTIVE);
        oldNotification.setUpdatedDate(LocalDateTime.now());

        notificationRepository.save(oldNotification);
        return oldNotification;
    }

}
