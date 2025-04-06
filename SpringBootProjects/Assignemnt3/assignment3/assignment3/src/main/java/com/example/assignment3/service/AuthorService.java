package com.example.assignment3.service;

import com.example.assignment3.exception.IdNotFoundException;
import com.example.assignment3.model.Author;
import com.example.assignment3.model.Book;
import com.example.assignment3.repository.AuthorRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepo authorRepo;

    public List<Author> getAllAuthors() {
        return authorRepo.findAll();
    }

    public Author getAuthorById(Long id) throws IdNotFoundException {
        return authorRepo.findById(id).orElseThrow(()->new IdNotFoundException("Author with id " + id + " not found"));
    }
    @Transactional
    public Author saveAuthor(Author author) {
        return authorRepo.save(author);
    }
    @Transactional
    public Author updateAuthor(Author author, Long id) throws IdNotFoundException {
        Author newAuthor = authorRepo.findById(id).orElseThrow(()->new IdNotFoundException("Author with id " + id + " not found"));
        newAuthor.setName(author.getName());
        return authorRepo.save(newAuthor);
    }
    @Transactional
    public void deleteAuthor(Long id) throws IdNotFoundException {
        if(authorRepo.existsById(id)) {
            authorRepo.deleteById(id);
        }else{
            throw new IdNotFoundException("Author with id " + id + " not found");
        }
    }
    @Transactional
    public List<Book> getBooksByAuthorId(Long id) throws IdNotFoundException {
        Author author = authorRepo.findById(id).orElseThrow(()->new IdNotFoundException("Author with id " + id + " not found"));
        return author.getBooks();
    }
}
