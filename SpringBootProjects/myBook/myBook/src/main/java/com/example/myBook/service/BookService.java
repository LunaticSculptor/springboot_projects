package com.example.myBook.service;

import com.example.myBook.exception.BookNotFoundException;
import com.example.myBook.model.Book;
import com.example.myBook.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private BookRepository bookRepository;
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Page<Book> findAllBooks(int page, int size, String sortBy, String direction) {
//        return bookRepository.findAll();
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return bookRepository.findAll(pageable);
    }

    public Optional<Book> findBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book with id: "+id+" not found"));
        return bookRepository.findById(id);
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(Book bookDetails, Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book with id: "+id+" not found"));
        book.setAuthor(bookDetails.getAuthor());
        book.setTitle(bookDetails.getTitle());
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book with id: "+id+" not found"));
        bookRepository.deleteById(id);
    }
}
