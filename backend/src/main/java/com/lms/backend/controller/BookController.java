package com.lms.backend.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
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

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Book createBook(@RequestBody BookRequestDto dto) {
        var book = new Book();
        BeanUtils.copyProperties(dto, book);
        return bookService.createBook(book);
    }

    @PutMapping("/{bookId}")
    @ResponseStatus(code = HttpStatus.OK)
    public Book updateBook(@RequestBody BookRequestDto dto, @PathVariable String bookId) {
        var book = new Book();
        BeanUtils.copyProperties(dto, book);
        return bookService.updateBook(book, bookId);
    }

    @DeleteMapping("/{bookId}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public Book deleteBook(@PathVariable String bookId) {
        return bookService.deleteBook(bookId);
    }

    @GetMapping
    public List<Book> getBooks() {
        return bookService.getBooks();
    }

    @GetMapping("/active")
    public List<Book> getAllActiveBooks() {
        return bookService.getAllActiveBooks();
    }

    @GetMapping("/inactive")
    public List<Book> getAllInactiveBooks() {
        return bookService.getAllDeactiveBooks();
    }

    @PatchMapping("/{bookId}/stock")
    @ResponseStatus(code = HttpStatus.OK)
    public Book updateStock(@PathVariable String bookId, @RequestParam int stock) {
        return bookService.updateStock(bookId, stock);
    }

}
