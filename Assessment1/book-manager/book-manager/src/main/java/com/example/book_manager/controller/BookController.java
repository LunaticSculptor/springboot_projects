package com.example.book_manager.controller;

import com.example.book_manager.model.Book;
import com.example.book_manager.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {
    private BookService bookService;
    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/api/books/{id}")
    public ResponseEntity<?> getBookById (@PathVariable Long id) {
        try {
            Book book = bookService.getBookById(id);
            return new ResponseEntity<>(book, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Id not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/api/books")
    public ResponseEntity<Book> addBook (@RequestBody Book book) {
        Book added_book = bookService.addBook(book);
        return new ResponseEntity<>(added_book, HttpStatus.CREATED);
    }

    @DeleteMapping("/api/books/{id}")
    public ResponseEntity<String> deleteBook (@PathVariable Long id) {
        String delete_result = bookService.removeBook(id);
        return new ResponseEntity<>(delete_result, HttpStatus.OK);
    }
}
