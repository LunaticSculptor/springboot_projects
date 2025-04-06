package com.example.microservices.repository;

import com.example.microservices.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepo extends JpaRepository<Question, Integer> {

    @Query(value = "SELECT * FROM question WHERE category = :categoryName ORDER BY RAND() LIMIT :numQuestions", nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(@Param("categoryName") String categoryName, @Param("numQuestions") Integer numQuestions);

    List<Question> findByCategory(String categoryName);

}
