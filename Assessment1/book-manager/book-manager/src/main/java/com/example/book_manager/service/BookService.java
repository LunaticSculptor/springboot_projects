package com.example.book_manager.service;

import com.example.book_manager.model.Book;
import com.example.book_manager.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private BookRepository bookRepository;
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Cacheable("books")
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Transactional
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    @Transactional
    public String removeBook(Long id) {
        if(bookRepository.findById(id).isEmpty()) {
            return "No such Id exists";
        }
        bookRepository.deleteById(id);
        return "Deleted id: " + id + " successfully";
    }
}
