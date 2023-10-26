package com.lms.backend.dto;

import java.time.LocalDateTime;

public record UserResponseDto(
        String userId, String firstName,
        String lastName, String email,
        String referenceNumber,
        String profilePic,
        LocalDateTime updatedDate,
        LocalDateTime createdDate,
        String createdBy,
        String updatedBy) {
}
