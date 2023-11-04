package com.lms.backend.services.impl;

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
       
        book.setBookId(bookId);
        book.setStatus(BookStatus.OUTOFSTOCK);

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

        bookRepository.save(oldBook);
        return oldBook;
    }

    @Override
    public Book deleteBook(String bookId) {
        var oldBook = bookRepository.findById(bookId).orElseThrow();
        
        oldBook.setStatus(BookStatus.INACTIVE);
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

    @Override
    public Book getBookById(String bookId) {
        return bookRepository.findById(bookId).orElseThrow();
    }

    @Override
    public Book bookOutOfStock(String bookId) {
        var book = bookRepository.findById(bookId).orElseThrow();

        book.setAvailableStock(0);
        book.setStatus(BookStatus.OUTOFSTOCK);

        bookRepository.save(book);
        return book;
    }

    @Override
    public List<Book> getBookByShelfNumber(String shelfNumber) {
        return bookRepository.findByShelfNumber(shelfNumber);
    }

    @Override
    public List<Book> getBookByBatchName(String batchName) {
        return bookRepository.findByBatchName(batchName);
    }

    @Override
    public List<Book> getBookBySemester(int semester) {
        return bookRepository.findBySemester(semester);
    }
}
