package com.lms.backend.dto;

/**
 * UserRequestDto
 */
public record UserRequestDto(
        String firstName,
        String latsName,
        String referenceNumber,
        String email,
        String password) {
}