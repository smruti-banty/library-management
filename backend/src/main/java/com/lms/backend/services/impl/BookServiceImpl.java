package com.lms.backend.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.lms.backend.constants.BookStatus;
import com.lms.backend.model.Book;
import com.lms.backend.repository.BookRepository;
import com.lms.backend.services.BookService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public Book createBook(Book book) {
        var bookId = UUID.randomUUID().toString();
        var currentDate = LocalDateTime.now();
        var status = BookStatus.OUTOFSTOCK;

        book.setBookId(bookId);
        book.setCreatedDate(currentDate);
        book.setUpdatedDate(currentDate);
        book.setStatus(status);

        bookRepository.save(book);
        return book;
    }

    @Override
    public Book updateBook(Book book, String bookId) {
        var oldBook = bookRepository.findById(bookId).orElseThrow();

        oldBook.setBookName(book.getBookName());
        oldBook.setAuthor(book.getAuthor());
        oldBook.setDescription(book.getDescription());
        oldBook.setReferenceNumber(book.getReferenceNumber());
        oldBook.setUpdatedDate(LocalDateTime.now());

        bookRepository.save(oldBook);
        return oldBook;
    }

    @Override
    public Book deleteBook(Book book, String bookId) {
        var oldBook = bookRepository.findById(bookId).orElseThrow();

        oldBook.setStatus(BookStatus.INACTIVE);
        oldBook.setUpdatedDate(LocalDateTime.now());

        bookRepository.save(oldBook);
        return oldBook;
    }

    @Override
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> getAllActiveBooks() {
        return bookRepository.findAllByStatus(BookStatus.ACTIVE);
    }

    @Override
    public List<Book> getAllInactiveBooks() {
        return bookRepository.findAllByStatus(BookStatus.INACTIVE);
    }

    @Override
    public Book updateStock(String bookId, int stock) {
        if (stock == 0) {
            throw new RuntimeException("stock can't be 0");
        }

        var book = bookRepository.findById(bookId).orElseThrow();
        book.setStatus(BookStatus.ACTIVE);
        book.setAvailableStock(stock);

        bookRepository.save(book);
        return book;
    }

}
