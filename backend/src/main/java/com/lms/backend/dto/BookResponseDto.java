package com.lms.backend.dto;

import java.time.LocalDateTime;

import com.lms.backend.constants.BookStatus;

public record BookResponseDto(
        String bookId,
        String bookName,
        String author,
        String description,
        String image,
        String referenceNumber,
        int availableStock,
        BookStatus status,
        String createdBy,
        LocalDateTime createdDate,
        String updatedBy,
        LocalDateTime updatedDate) {

}
