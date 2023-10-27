package com.lms.backend.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

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
        var notificationId = UUID.randomUUID().toString();
        var createdDate = LocalDateTime.now();
        var updatedDate = LocalDateTime.now();
        var status = NotificationStatus.UNREAD;
        var state = NotificationStateStatus.ACTIVE;

        notification.setNotificationId(notificationId);
        notification.setCreatedDate(createdDate);
        notification.setStatus(status);
        notification.setState(state);
        notification.setUpdatedDate(updatedDate);

        notificationRepository.save(notification);
        return notification;
    }

    @Override
    public List<Notification> markAsRead(String receiverUserId) {
        System.out.println("Impl : " + receiverUserId);
        var notifications = notificationRepository.findAllByReceiverUserIdAndStatus(receiverUserId,
                NotificationStatus.UNREAD);

        System.out.println(notifications);
        var lists = notifications.stream().map(notification -> {
            notification.setStatus(NotificationStatus.READ);
            return notification;
        }).toList();

        notificationRepository.saveAll(lists);
        return lists;
    }

    @Override
    public List<Notification> getAllNotification() {
        return notificationRepository.findAllByState(NotificationStateStatus.ACTIVE);
    }

    @Override
    public Notification deleteNotification(String notificationId) {
        var oldNotification = notificationRepository.findById(notificationId).orElseThrow();

        oldNotification.setState(NotificationStateStatus.INACTIVE);
        oldNotification.setUpdatedDate(LocalDateTime.now());

        notificationRepository.save(oldNotification);
        return oldNotification;
    }

    @Override
    public List<Notification> findAllByReceiverUserIdStatus(String receiverUserId, NotificationStatus status) {
        return notificationRepository.findAllByReceiverUserIdAndStatus(receiverUserId, status);
    }

}
