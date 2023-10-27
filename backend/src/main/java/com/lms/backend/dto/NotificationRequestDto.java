package com.lms.backend.dto;

import com.lms.backend.constants.NotificationStatus;

public record NotificationRequestDto(
        String message,
        NotificationStatus status,
        String senderUserId,
        String reciverUserId) {

}
