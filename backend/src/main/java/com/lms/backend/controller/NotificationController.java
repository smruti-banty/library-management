package com.lms.backend.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lms.backend.constants.NotificationStatus;
import com.lms.backend.dto.NotificationRequestDto;
import com.lms.backend.model.Notification;
import com.lms.backend.services.NotificationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notification")
@Tag(name = "Notification controller", description = "Manage notification")
public class NotificationController {
    private final NotificationService notificationService;

    @GetMapping
    @Operation(summary = "Get all notification", description = "To retrieve all notification")
    public List<Notification> getAllNotifications() {
        return notificationService.getAllNotification();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation(summary = "create notification", description = "send appropriate filed to create notification")
    public Notification createNotification(@RequestBody NotificationRequestDto dto) {
        Notification notification = new Notification();
        BeanUtils.copyProperties(dto, notification);
        return notificationService.sendNotification(notification);
    }

    @PostMapping("/{receiverUserId}")
    @Operation(summary = "Mark as read", description = "Mark as read of all unread notification of a particular user")
    public List<Notification> markAsReadNotification(@PathVariable String receiverUserId) {
        return notificationService.markAsRead(receiverUserId);
    }

    @PostMapping("/{receiverUserId}/unread")
    @Operation(summary = "get all unread notification", description = "get all unread notification for a particular user")
    public List<Notification> getAllUnreadNotifications(@PathVariable String receiverUserId) {
        return notificationService.findAllByReceiverUserIdStatus(receiverUserId, NotificationStatus.UNREAD);
    }

    @PostMapping("/{receiverUserId}/read")
    @Operation(summary = "get all read notification", description = "get all read notification for a particular user")
    public List<Notification> getAllReadNotifications(@PathVariable String receiverUserId) {
        return notificationService.findAllByReceiverUserIdStatus(receiverUserId, NotificationStatus.READ);
    }
}
