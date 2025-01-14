package com.example.demo.controller;

import com.example.demo.entity.Book;
import com.example.demo.service.BookService;
import com.example.demo.service.BookServiceImpl;
import jakarta.persistence.PostUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/")
public class BookController {
    @Autowired
    private BookServiceImpl bookService;

    @GetMapping("/books")
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }

    @PostMapping("/books")
    public Book createBook(@RequestBody Book book){
        return bookService.createBook(book);
    }

    @PutMapping("/books")
    public Book updateBook(@RequestBody Book book){
        return bookService.updateBook(book);
    }

    @DeleteMapping("/books")
    public void deleteBook() {bookService.deleteBook(); }

    @DeleteMapping("/books/{id}")
    public Book deleteBookById(@PathVariable Long id) {return bookService.deleteBookById(id); }

    @GetMapping("/books/{title}")
    public List<Book> getBookByTitle(@PathVariable String title) { return bookService.getBookByTitle(title); }

    @GetMapping("/books/price/{threshold}")
    public List<Book> getBooksAboveThreshold(@PathVariable Double threshold) {
        return bookService.getBooksAboveThreshold(threshold);
    }

    @GetMapping("/books/max")
    public Double getMaxPrice(){
        return bookService.getMaxPrice();
    }
}
