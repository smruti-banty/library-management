package com.lms.backend.services.impl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.lms.backend.constants.BookIssueStatus;
import com.lms.backend.model.BookIssue;
import com.lms.backend.repository.BookIssueRepository;
import com.lms.backend.services.BookIssueService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookIssueServiceImpl implements BookIssueService {
    private final BookIssueRepository bookIssueRepository;

    @Override
    public BookIssue issueBook(BookIssue bookIssue) {
        var bookIssueId = UUID.randomUUID().toString();
        var issueStatus = BookIssueStatus.ISSUED;
        var issueDate = LocalDateTime.now();

        bookIssue.setBookIssueId(bookIssueId);
        bookIssue.setStatus(issueStatus);
        bookIssue.setCreatedDate(issueDate);
        bookIssue.setUpdatedDate(issueDate);

        bookIssueRepository.save(bookIssue);
        return bookIssue;
    }

    @Override
    public BookIssue returnBook(String bookIssueId) {
        var existingIssuedBook = bookIssueRepository.findById(bookIssueId).orElseThrow();
        existingIssuedBook.setStatus(BookIssueStatus.RETURNED);

        bookIssueRepository.save(existingIssuedBook);
        return existingIssuedBook;
    }

}
