package com.lms.backend.services.impl;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Service;

import com.lms.backend.constants.BookIssueStatus;
import com.lms.backend.model.BookIssue;
import com.lms.backend.repository.BookIssueRepository;
import com.lms.backend.repository.BookRepository;
import com.lms.backend.services.BookIssueService;
import com.lms.backend.services.TransactionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookIssueServiceImpl implements BookIssueService {
    private final BookIssueRepository bookIssueRepository;
    private final BookRepository bookRepository;
    private final TransactionService transactionService;

    @Override
    public BookIssue issueBook(BookIssue bookIssue) {
        var bookIssueId = UUID.randomUUID().toString();
        var issueStatus = BookIssueStatus.ISSUED;
        var issueDate = LocalDateTime.now();
        var bookId = bookIssue.getBookId();
        var book = bookRepository.findById(bookId).orElseThrow();

        bookIssue.setBookIssueId(bookIssueId);
        bookIssue.setStatus(issueStatus);
        bookIssue.setCreatedDate(issueDate);
        bookIssue.setUpdatedDate(issueDate);

        BookIssue savedBookIssue = bookIssueRepository.save(bookIssue);
        CompletableFuture.runAsync(() -> {
            if (savedBookIssue.getBookIssueId() != null) {
                int avaiableStock = book.getAvailableStock() - 1;
                book.setAvailableStock(avaiableStock);
                bookRepository.save(book);
                transactionService.createTransaction(savedBookIssue);
            }
        });

        return bookIssue;
    }

    @Override
    public BookIssue returnBook(String bookIssueId) {
        var existingIssuedBook = bookIssueRepository.findById(bookIssueId).orElseThrow();
        existingIssuedBook.setStatus(BookIssueStatus.RETURNED);
        var bookId = existingIssuedBook.getBookId();
        var book = bookRepository.findById(bookId).orElseThrow();

        BookIssue savedBookIssue = bookIssueRepository.save(existingIssuedBook);

        CompletableFuture.runAsync(() -> {
            if (savedBookIssue.getBookIssueId() != null) {
                int avaiableStock = book.getAvailableStock() + 1;
                book.setAvailableStock(avaiableStock);
                bookRepository.save(book);
                transactionService.updateTransaction(bookIssueId);
            }
        });

        return existingIssuedBook;

    }

}
