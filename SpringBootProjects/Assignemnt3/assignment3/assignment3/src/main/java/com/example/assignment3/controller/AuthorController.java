package com.example.assignment3.controller;

import com.example.assignment3.exception.IdNotFoundException;
import com.example.assignment3.model.Author;
import com.example.assignment3.model.Book;
import com.example.assignment3.service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<Author>> getAuthors() {
        return new ResponseEntity<>(authorService.getAllAuthors(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id) throws IdNotFoundException {
        return new ResponseEntity<>(authorService.getAuthorById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Author> saveAuthor(@Valid @RequestBody Author author) {
        return new ResponseEntity<>(authorService.saveAuthor(author), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(@RequestBody Author author, @PathVariable Long id) throws IdNotFoundException {
        return new ResponseEntity<>(authorService.updateAuthor(author, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable Long id) throws IdNotFoundException {
        authorService.deleteAuthor(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

    @GetMapping("/{id}/books")
    public ResponseEntity<List<Book>> getAllBooksByAuthor(@PathVariable Long id) throws IdNotFoundException {
        return new ResponseEntity<>(authorService.getBooksByAuthorId(id), HttpStatus.OK);
    }
}
