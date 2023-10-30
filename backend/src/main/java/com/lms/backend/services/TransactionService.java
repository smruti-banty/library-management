package com.lms.backend.services;

import com.lms.backend.model.BookIssue;
import com.lms.backend.model.Transaction;

/**
 * TransactionService
 */
public interface TransactionService {
    Transaction createTransaction(BookIssue bookIssue);

    void updateTransaction(String bookIssueId);

}