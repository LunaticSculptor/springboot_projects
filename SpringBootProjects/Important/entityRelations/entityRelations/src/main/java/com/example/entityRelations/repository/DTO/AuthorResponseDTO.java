package com.example.entityRelations.repository.DTO;

import java.util.List;

public class AuthorResponseDTO {
    private Long id;
    private String name;
    private List<BookDTO> books;

    public AuthorResponseDTO(Long id, String name, List<BookDTO> books) {
        this.id = id;
        this.name = name;
        this.books = books;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BookDTO> getBooks() {
        return books;
    }

    public void setBooks(List<BookDTO> books) {
        this.books = books;
    }
}
