package com.lms.backend.dto;

public record BookRequestDto(
                String bookName,
                String referenceNumber,
                String description,
                String author) {
}

// List<String> batch is missing
