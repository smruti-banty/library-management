package com.lms.backend.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lms.backend.dto.BookRequestDto;
import com.lms.backend.model.Book;
import com.lms.backend.services.BookService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/book")
@RequiredArgsConstructor
@Tag(name = "Book controller", description = "Manage books")
public class BookController {
    private final BookService bookService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation(summary = "Create book", description = "create a new book")
    public Book createBook(@RequestBody BookRequestDto dto) {
        var book = new Book();
        BeanUtils.copyProperties(dto, book);
        return bookService.createBook(book);
    }

    @PutMapping("/{bookId}")
    @Operation(summary = "Update book", description = "Update book basic details")
    @ApiResponse(responseCode = "200", description = "On successful update")
    @ApiResponse(responseCode = "500", description = "book not found", content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    public Book updateBook(@RequestBody BookRequestDto dto, @PathVariable String bookId) {
        var book = new Book();
        BeanUtils.copyProperties(dto, book);
        return bookService.updateBook(book, bookId);
    }

    @DeleteMapping("/{bookId}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    @Operation(summary = "Delete book", description = "Inactive a book by book id")
    @ApiResponse(responseCode = "200", description = "On successful delete")
    @ApiResponse(responseCode = "500", description = "Book not found", content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    public Book deleteBook(@PathVariable String bookId) {
        return bookService.deleteBook(bookId);
    }

    @GetMapping
    @Operation(summary = "Get books", description = "To retrive all books data")
    public List<Book> getBooks() {
        return bookService.getBooks();
    }

    @GetMapping("/active")
    @Operation(summary = "Get active books", description = "To retrive all active books data")
    public List<Book> getAllActiveBooks() {
        return bookService.getAllActiveBooks();
    }

    @GetMapping("/inactive")
    @Operation(summary = "Get inactive books", description = "To retrive all inactive books data")
    public List<Book> getAllInactiveBooks() {
        return bookService.getAllInactiveBooks();
    }

    @PatchMapping("/{bookId}/stock")
    @Operation(summary = "update book stock", description = "Update available stock of book")
    @ApiResponse(responseCode = "200", description = "On successful update")
    @ApiResponse(responseCode = "500", description = "Book not found", content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    public Book updateStock(@PathVariable String bookId, @RequestParam int stock) {
        return bookService.updateStock(bookId, stock);
    }

    @GetMapping("/{bookId}")
    @Operation(summary = "Get book by id", description = "Retrive bokk by book id")
    @ApiResponse(responseCode = "200", description = "On successful retrival")
    @ApiResponse(responseCode = "500", description = "Book not found", content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    public Book getBookById(@PathVariable String bookId) {
        return bookService.getBookById(bookId);
    }

    @GetMapping("/byShelf/{shelfNumber}")
    @Operation(summary = "Get books by shelf number", description = "To retrive all the  books in the given shelf")
    public List<Book> getBooksByShelfNumber(@PathVariable String shelfNumber) {
        return bookService.getBookByShelfNumber(shelfNumber);
    }

    @GetMapping("/byBatch/{batchName}")
    @Operation(summary = "Get books by batch name", description = "To retrive all the  books for the given batch")
    public List<Book> getBooksByBatchName(@PathVariable String batchName) {
        return bookService.getBookByBatchName(batchName);
    }

    @GetMapping("/bySemester/{semester}")
    @Operation(summary = "Get books by semester", description = "To retrive all the  books for that semester")
    public List<Book> getBooksBySemester(@PathVariable int semester) {
        return bookService.getBookBySemester(semester);
    }

}
