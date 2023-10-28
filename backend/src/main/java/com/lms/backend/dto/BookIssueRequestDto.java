package com.lms.backend.dto;

public record BookIssueRequestDto(
                String adminId,
                String bookId,
                String bookRefferenceNumber,
                String studentId) {

}
