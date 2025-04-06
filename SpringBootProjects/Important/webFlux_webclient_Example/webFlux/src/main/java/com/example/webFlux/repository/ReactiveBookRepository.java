package com.example.webFlux.repository;
import com.example.webFlux.model.Book;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
@Repository
public interface ReactiveBookRepository extends ReactiveCrudRepository<Book, Long> {
    // Additional query methods can be added here
}