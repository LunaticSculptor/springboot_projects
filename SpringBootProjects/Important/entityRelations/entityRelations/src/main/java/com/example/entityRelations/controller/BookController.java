package com.example.entityRelations.controller;

import com.example.entityRelations.exception.IdNotFoundException;
import com.example.entityRelations.model.Author;
import com.example.entityRelations.model.Book;
import com.example.entityRelations.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<Book>> getBooks() {
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return new ResponseEntity<>(bookService.getBookById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Book> saveBook(@RequestBody Book book) throws IdNotFoundException {
//        return new ResponseEntity<>(bookService.saveBook(book), HttpStatus.CREATED);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.saveBook(book));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) throws IdNotFoundException {
//        return new ResponseEntity<>(bookService.updateBook(id, book), HttpStatus.OK);
        return ResponseEntity.ok(bookService.updateBook(id, book));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) throws IdNotFoundException {
        bookService.deleteBook(id);
//        return new ResponseEntity<>(HttpStatus.OK);
        return ResponseEntity.ok().build();
    }
}
