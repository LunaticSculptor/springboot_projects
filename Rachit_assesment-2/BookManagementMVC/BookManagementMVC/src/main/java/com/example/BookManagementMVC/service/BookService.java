package com.example.BookManagementMVC.service;

import com.example.BookManagementMVC.exceptions.IdNotFoundException;
import com.example.BookManagementMVC.model.Book;
import com.example.BookManagementMVC.repository.BookRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepo bookRepo;

    public BookService(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }

    public Book getBookById(Long id) throws IdNotFoundException {
        return bookRepo.findById(id).orElseThrow(() -> new IdNotFoundException("No such Id exists"));
    }

    public Book saveBook(Book book) {
        return bookRepo.save(book);
    }

    public Book updateBook(Long id, Book book) throws IdNotFoundException {
        Book existingBook = bookRepo.findById(id).orElse(null);
        if(existingBook == null) {
            throw new IdNotFoundException("No such id");
        }
        existingBook.setAuthor(book.getAuthor());
        existingBook.setTitle(book.getTitle());
        bookRepo.save(existingBook);
        return existingBook;
    }

    public void deleteBook(Long id) throws IdNotFoundException{
        if(!bookRepo.existsById(id)) {
            throw new IdNotFoundException("No such id");
        }
        bookRepo.deleteById(id);
    }
}
