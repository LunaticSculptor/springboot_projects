package com.example.entityRelations.repository.DTO;

public class BookDTO {
    private Long id;
    private String title;

    public BookDTO(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

