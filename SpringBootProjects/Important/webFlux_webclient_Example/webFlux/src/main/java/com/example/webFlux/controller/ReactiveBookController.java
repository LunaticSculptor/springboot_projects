package com.example.webFlux.controller;
import com.example.webFlux.model.Book;
import com.example.webFlux.service.ReactiveBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@RestController
@RequestMapping("/api/reactive/books")
public class ReactiveBookController {
    private final ReactiveBookService bookService;
    @Autowired
    public ReactiveBookController(ReactiveBookService bookService) {
        this.bookService = bookService;
    }

    // GET: Fetch all books (Reactive)
    @GetMapping
    public Flux<Book> getAllBooks() {
        return bookService.findAllBooks();
    }

    // GET: Fetch a book by ID (Reactive)
    @GetMapping("/{id}")
    public Mono<ResponseEntity<Book>> getBookById(@PathVariable Long id) {
        return bookService.findBookById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    // POST: Create or update a book (Reactive)
    @PostMapping
    public Mono<ResponseEntity<Book>> createBook(@RequestBody Book book) {
        return bookService.saveBook(book)
                .map(ResponseEntity::ok);
    }

    // DELETE: Remove a book by ID (Reactive)
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteBook(@PathVariable Long id) {
        return bookService.deleteBook(id)
                .then(Mono.just(ResponseEntity.noContent().build()));
    }
}