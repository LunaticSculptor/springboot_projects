package com.example.entityRelations.controller;

import com.example.entityRelations.exception.IdNotFoundException;
import com.example.entityRelations.model.Author;
import com.example.entityRelations.model.Book;
import com.example.entityRelations.repository.DTO.AuthorResponseDTO;
import com.example.entityRelations.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;
    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<List<AuthorResponseDTO>> getAuthors() {
        return new ResponseEntity<>(authorService.getAllAuthors(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id) throws IdNotFoundException {
        return new ResponseEntity<>(authorService.getAuthorById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Author> saveAuthor(@RequestBody Author author) {
        return new ResponseEntity<>(authorService.saveAuthor(author), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(@RequestBody Author author, @PathVariable Long id) throws IdNotFoundException {
        return new ResponseEntity<>(authorService.updateAuthor(author, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable Long id) throws IdNotFoundException {
        authorService.deleteAuthor(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}/books")
    public ResponseEntity<List<Book>> getAllBooksByAuthor(@PathVariable Long id) throws IdNotFoundException {
        return new ResponseEntity<>(authorService.getBooksByAuthorId(id), HttpStatus.OK);
    }
}
