package com.lms.backend.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lms.backend.dto.BookIssueRequestDto;
import com.lms.backend.model.BookIssue;
import com.lms.backend.services.BookIssueService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/book/issue")
@RequiredArgsConstructor
@Tag(name = "Issue Book controller", description = "Manage issue book to student")
public class BookIssueController {
    private final BookIssueService bookIssueService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation(summary = "issue book", description = "issue a new book")
    public BookIssue issueBook(@RequestBody BookIssueRequestDto dto) {
        BookIssue bookIssue = new BookIssue();
        BeanUtils.copyProperties(dto, bookIssue);
        return bookIssueService.issueBook(bookIssue);
    }

    @PostMapping("/{issueBookId}/return")
    @Operation(summary = "return book", description = "return the issued book")
    public BookIssue returnBook(@PathVariable String issueBookId) {
        return bookIssueService.returnBook(issueBookId);
    }
}
