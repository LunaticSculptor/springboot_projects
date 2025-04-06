package com.example.assignment3.service;

import com.example.assignment3.exception.IdNotFoundException;
import com.example.assignment3.model.Author;
import com.example.assignment3.model.Book;
import com.example.assignment3.repository.AuthorRepo;
import com.example.assignment3.repository.BookRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepo bookRepo;
    private final AuthorRepo authorRepo;

    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }

    public Book getBookById(Long id) throws IdNotFoundException {
        return bookRepo.findById(id).orElseThrow(()->new IdNotFoundException("No such book by id "+id+" exists"));
    }

    @Transactional
    public Book saveBook(Book book) throws IdNotFoundException {
        if (book.getAuthor() != null && book.getAuthor().getAuthorId() != null) {
            Author author = authorRepo.findById(book.getAuthor().getAuthorId()).orElseThrow(()->new IdNotFoundException("No author with id "+book.getAuthor().getAuthorId()+" exists"));
            book.setAuthor(author);
        }
        return bookRepo.save(book);
    }

    @Transactional
    public Book updateBook(Long id, Book book) throws IdNotFoundException {
        Book existingBook = bookRepo.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Book with id " + id + " not found"));

        existingBook.setTitle(book.getTitle());
        if (book.getAuthor() != null && book.getAuthor().getAuthorId() != null) {
            Author author = authorRepo.findById(book.getAuthor().getAuthorId()).orElseThrow(()->new IdNotFoundException("No author with id "+book.getAuthor().getAuthorId()+" exists"));
//            System.out.println(author);
            existingBook.setAuthor(author);
        }

        return bookRepo.save(existingBook);
    }

    @Transactional
    public void deleteBook(Long id) throws IdNotFoundException {
        if(bookRepo.existsById(id)) {
            bookRepo.deleteById(id);
        }else{
            throw new IdNotFoundException("Book with id " + id + " not found");
        }
    }
}
