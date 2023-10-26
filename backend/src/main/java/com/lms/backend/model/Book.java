package com.lms.backend.model;

import java.time.LocalDateTime;
// import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import com.lms.backend.constants.BookStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * Book
 */
@Document
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Book {
    @Id
    private String bookId;
    private String bookName;
    private String author;
    private String description;
    private String image;
    private String referenceNumber;
    // private List<Batch> batch;
    private int availableStock;
    @Field(targetType = FieldType.STRING)
    private BookStatus status;
    private LocalDateTime createdDate;
    private String createdBy;
    private LocalDateTime updatedDate;
    private String updatedBy;
}