package com.lms.backend.dto;

public record UserResponseDto(
        String firstName,
        String lastName, String email,
        String referenceNumber,
        String profilePic) {
}
