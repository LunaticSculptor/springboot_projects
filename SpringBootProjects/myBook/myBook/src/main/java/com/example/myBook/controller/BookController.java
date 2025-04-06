package com.example.myBook.controller;

import com.example.myBook.model.Book;
import com.example.myBook.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private BookService bookService;
    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<Page<Book>> getAllBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "title") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {
        Page<Book> books = bookService.findAllBooks(page, size, sortBy, direction);
        return ResponseEntity.ok(books);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Book> getBooksById(@PathVariable Long id) {
//        Book book = bookService.findBookById(id).orElse(null);
//        return new ResponseEntity<>(book, HttpStatus.OK);
//    }
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Book>> getBookById(@PathVariable Long id) {
        Optional<Book> book = bookService.findBookById(id);
        if (book.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // Create HATEOAS links
        EntityModel<Book> resource = EntityModel.of(book.get());
        Link selfLink = linkTo(methodOn(BookController.class).getBookById(id)).withSelfRel();
        Link updateLink = linkTo(methodOn(BookController.class).updateBook(null, id)).withRel("update").withType("PUT");
        Link deleteLink = linkTo(methodOn(BookController.class).deleteBook(id)).withRel("delete").withType("DELETE");
        resource.add(selfLink, updateLink, deleteLink);
        return ResponseEntity.ok(resource);
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@Valid @RequestBody Book book) {
        return new ResponseEntity<>(bookService.saveBook(book), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@Valid @RequestBody Book book, @PathVariable Long id) {
        return new ResponseEntity<>(bookService.updateBook(book, id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
