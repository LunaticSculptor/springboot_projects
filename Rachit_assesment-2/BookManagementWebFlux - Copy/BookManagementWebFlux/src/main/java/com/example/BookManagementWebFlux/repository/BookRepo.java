package com.example.BookManagementWebFlux.repository;


import com.example.BookManagementWebFlux.model.Book;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface BookRepo extends ReactiveCrudRepository<Book, Long> {

}
