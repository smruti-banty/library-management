package com.lms.backend.dto;

public record BookIssueRequestDto(
        String liberianUuid,
        String bookId,
        String bookRefferenceNumber,
        String studentUuid) {

}
