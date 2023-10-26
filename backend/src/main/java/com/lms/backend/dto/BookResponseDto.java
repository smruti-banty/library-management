package com.lms.backend.dto;

import java.time.LocalDateTime;
import java.util.UUID;


import com.lms.backend.constants.BookStatus;

public record BookResponseDto(
        UUID bookId,
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
