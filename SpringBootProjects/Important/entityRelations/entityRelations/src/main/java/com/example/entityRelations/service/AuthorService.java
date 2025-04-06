package com.example.entityRelations.service;

import com.example.entityRelations.exception.IdNotFoundException;
import com.example.entityRelations.model.Author;
import com.example.entityRelations.model.Book;
import com.example.entityRelations.repository.AuthorRepository;
import com.example.entityRelations.repository.DTO.AuthorResponseDTO;
import com.example.entityRelations.repository.DTO.BookDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    private AuthorRepository authorRepository;
    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<AuthorResponseDTO> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        return authors.stream()
                .map(author -> new AuthorResponseDTO(
                        author.getId(),
                        author.getName(),
                        author.getBooks().stream()
                                .map(book -> new BookDTO(book.getId(), book.getTitle()))
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
    }

    public Author getAuthorById(Long id) throws IdNotFoundException {
        return authorRepository.findById(id).orElseThrow(()->new IdNotFoundException("Author with id " + id + " not found"));
    }
    @Transactional
    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }
    @Transactional
    public Author updateAuthor(Author author, Long id) throws IdNotFoundException {
        Author newAuthor = authorRepository.findById(id).orElseThrow(()->new IdNotFoundException("Author with id " + id + " not found"));
        newAuthor.setName(author.getName());
        return authorRepository.save(newAuthor);
    }
    @Transactional
    public void deleteAuthor(Long id) throws IdNotFoundException {
        if(authorRepository.existsById(id)) {
            authorRepository.deleteById(id);
        }else{
            throw new IdNotFoundException("Author with id " + id + " not found");
        }
    }
    @Transactional
    public List<Book> getBooksByAuthorId(Long id) throws IdNotFoundException {
        Author author = authorRepository.findById(id).orElseThrow(()->new IdNotFoundException("Author with id " + id + " not found"));
        return author.getBooks();
    }
}
