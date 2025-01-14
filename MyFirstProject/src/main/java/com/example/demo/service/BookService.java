package com.example.demo.service;

import com.example.demo.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();

    Book createBook(Book book);

    void deleteBook();

    Book updateBook(Book book);

    Book deleteBookById(Long id);

    List<Book> getBookByTitle(String title);

    List<Book> getBooksAboveThreshold(Double threshold);
}
