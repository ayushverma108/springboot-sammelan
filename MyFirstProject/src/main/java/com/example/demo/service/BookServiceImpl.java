package com.example.demo.service;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class BookServiceImpl implements BookService{
    @Autowired
    public BookRepo bookRepo;

    @Override
    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }

    @Override
    public Book createBook(Book book) {
        return bookRepo.save(book);
    }

    @Override
    public void deleteBook() {
        bookRepo.deleteAll();
    }

    @Override
    public Book updateBook(Book book) {
        return bookRepo.save(book);
    }

    @Override
    public Book deleteBookById(Long id) {
        Book book = bookRepo.findById(id).get();
        bookRepo.delete(book);
        return book;
    }

    @Override
    public List<Book> getBookByTitle(String title) {
        return bookRepo.findAllByTitle(title);
    }

    @Override
    public List<Book> getBooksAboveThreshold(Double threshold) {
//        List<Book> book = bookRepo.findAll();
//        List<Book> output = new ArrayList<>();
//        for(Book b: book){
//            if(b.getPrice() > threshold){
//                output.add(b);
//            }
//        }
//        return output;

        return bookRepo.findAll().stream().filter((a)->a.getPrice()>threshold).toList();
    }

    @Override
    public Double getMaxPrice() {
//        double price = 0.0;
//        for(var b : bookRepo.findAll()){
//            price += b.getPrice()*b.getQuantityOrdered();
//        }
//        return price;

        return bookRepo.findAll().stream().mapToDouble((a)->a.getPrice()*a.getQuantityOrdered()).sum();
    }

    @Override
    public List<Book> getBooksSortedByTitle() {
        return getAllBooks().stream().sorted(Comparator.comparing(Book::getTitle).thenComparing(Book::getQuantityOrdered)).toList();
    }

    @Override
    public List<Book> getFilteredData(Double minPrice, String sortBy, String order) {
        List<Book> bookPrice = bookRepo.findAll().stream().filter((a)->a.getPrice()>minPrice).toList();
        List<Book> sort = new ArrayList<>(switch (sortBy) {
            case "title" -> bookPrice.stream().sorted(Comparator.comparing(Book::getTitle)).toList();
            case "author" -> bookPrice.stream().sorted(Comparator.comparing(Book::getAuthor)).toList();
            case "date" -> bookPrice.stream().sorted(Comparator.comparing(Book::getDate)).toList();
            default -> bookPrice.stream().sorted(Comparator.comparing(Book::getPrice)).toList();
        });
        if(order.equals("desc")){
            Collections.reverse(sort);
            return sort;
        }
        return sort;
    }
}
