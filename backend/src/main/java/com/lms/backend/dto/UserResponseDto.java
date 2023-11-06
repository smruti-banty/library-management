package com.lms.backend.dto;

public record UserResponseDto(
                String userId, String firstName,
                String lastName, String email,
                String referenceNumber,
                String profilePic) {
}
