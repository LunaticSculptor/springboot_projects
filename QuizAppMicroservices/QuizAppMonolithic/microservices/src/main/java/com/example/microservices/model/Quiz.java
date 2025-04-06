package com.example.microservices.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;

    @ManyToMany
//    @JoinTable(
//            name = "quiz_question",  // Join table name
//            joinColumns = @JoinColumn(name = "quiz_id"),
//            inverseJoinColumns = @JoinColumn(name = "question_id")
//    )
    private List<Question> questions;
}
