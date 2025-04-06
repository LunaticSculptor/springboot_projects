package com.example.BookManagementWebFlux.service;

import com.example.BookManagementWebFlux.model.Book;
import com.example.BookManagementWebFlux.repository.BookRepo;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BookService {

    private final BookRepo bookRepo;

    public BookService(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    public Flux<Book> getAllBooks() {
        return bookRepo.findAll();
    }

    public Mono<Book> getBookById(Long id) {
        return bookRepo.findById(id);
    }

    public Mono<Book> saveBook(Book book) {
        return bookRepo.save(book);
    }

    public Mono<Void> deleteBook(Long id) {
        return bookRepo.deleteById(id);
    }
}
