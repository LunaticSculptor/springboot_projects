package com.example.entityRelations.service;

import com.example.entityRelations.exception.IdNotFoundException;
import com.example.entityRelations.model.Author;
import com.example.entityRelations.model.Book;
import com.example.entityRelations.model.Category;
import com.example.entityRelations.repository.AuthorRepository;
import com.example.entityRelations.repository.BookRepository;
import com.example.entityRelations.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    private BookRepository bookRepository;
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    private AuthorRepository authorRepository;
    @Autowired
    public void setAuthorRepository(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }
    private CategoryRepository categoryRepository;
    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(RuntimeException::new);
    }
    @Transactional
    public Book saveBook(Book book) {
        if (book.getAuthor() != null && book.getAuthor().getId() != null) {
            Author author = authorRepository.findById(book.getAuthor().getId()).orElse(null);
            book.setAuthor(author);
        }

        if (book.getCategories() != null && !book.getCategories().isEmpty()) {
            List<Category> categories = book.getCategories().stream()
                    .map(category -> categoryRepository.findById(category.getId()).orElse(null))
                    .collect(Collectors.toList());
            book.setCategories(categories);
        }

        return bookRepository.save(book);
    }
    @Transactional
    public Book updateBook(Long id, Book book) throws IdNotFoundException {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Book with id " + id + " not found"));

        existingBook.setTitle(book.getTitle());

        if (book.getAuthor() != null && book.getAuthor().getId() != null) {
            Author author = authorRepository.findById(book.getAuthor().getId()).orElse(null);
            existingBook.setAuthor(author);
        }

        if (book.getCategories() != null && !book.getCategories().isEmpty()) {
            List<Category> categories = book.getCategories().stream()
                    .map(category -> categoryRepository.findById(category.getId()).orElse(null))
                    .collect(Collectors.toList());
            existingBook.setCategories(categories);
        }

        return bookRepository.save(existingBook);
    }
    @Transactional
    public void deleteBook(Long id) throws IdNotFoundException {
        if(bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
        }else{
            throw new IdNotFoundException("Book with id " + id + " not found");
        }
    }
}
