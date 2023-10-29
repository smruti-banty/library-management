package com.lms.backend.services;

import java.util.List;

import com.lms.backend.model.Book;

public interface BookService {
    Book createBook(Book book);

    Book updateBook(Book book, String bookId);

    Book deleteBook(String bookId);

    Book getBookById(String bookId);

    List<Book> getBooks();

    List<Book> getAllActiveBooks();

    List<Book> getAllInactiveBooks();

    Book updateStock(String bookId, int stock);

    Book bookOutOfStock(String bookId);

}
