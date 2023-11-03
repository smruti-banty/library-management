package com.lms.backend.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public record UserResponseDto(
        String userId, String firstName,
        String lastName, String email,
        String referenceNumber,
        String profilePic,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime updatedDate,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime createdDate,
        String createdBy,
        String updatedBy) {
}
