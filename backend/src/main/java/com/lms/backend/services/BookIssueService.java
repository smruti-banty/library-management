package com.lms.backend.services;

import com.lms.backend.model.BookIssue;

public interface BookIssueService {
    BookIssue issueBook(BookIssue bookIssue);

    BookIssue returnBook(String bookIssueId);

}
