package com.example.webFlux.service;
import com.example.webFlux.model.Book;
import com.example.webFlux.repository.ReactiveBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
public class ReactiveBookService {
    private final ReactiveBookRepository bookRepository;
    @Autowired
    public ReactiveBookService(ReactiveBookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    // Fetch all books
    public Flux<Book> findAllBooks() {
        return bookRepository.findAll();
    }
    // Fetch a book by ID
    public Mono<Book> findBookById(Long id) {
        return bookRepository.findById(id);
    }
    // Add or update a book
    public Mono<Book> saveBook(Book book) {
        return bookRepository.save(book);
    }
    // Delete a book by ID
    public Mono<Void> deleteBook(Long id) {
        return bookRepository.deleteById(id);
    }
}