package com.lms.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.lms.backend.constants.BookIssueStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Transaction {
    @Id
    private String transactionId;
    private String adminId;
    private String bookReferenceNumber;
    private String studentId;
    private BookIssueStatus status;
}
