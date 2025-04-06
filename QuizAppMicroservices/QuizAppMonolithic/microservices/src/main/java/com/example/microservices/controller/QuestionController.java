package com.example.microservices.controller;

import com.example.microservices.model.Question;
import com.example.microservices.model.QuestionWrapper;
import com.example.microservices.model.Response;
import com.example.microservices.service.QuestionService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/question")
public class QuestionController {

    private QuestionService questionService;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        return ResponseEntity.ok(questionService.getAllQuestions());
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category) {
        return ResponseEntity.ok(questionService.getQuestionsByCategory(category));
    }

    @PostMapping("add")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
        return  ResponseEntity.ok(questionService.addQuestion(question));
    }

//    @GetMapping("generate")
//    public ResponseEntity<List<Integer>> getQuestionsForQuiz
//            (@RequestParam String categoryName, @RequestParam Integer numQuestions) {
//        return ResponseEntity.ok(questionService.getQuestionsForQuiz(categoryName, numQuestions));
//    }
//
//    @PostMapping("getQuestions")
//    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds) {
//        return ResponseEntity.ok(questionService.getQuestionsFromId(questionIds));
//    }
//
//    @PostMapping("getScore")
//    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses) {
//        return ResponseEntity.ok(questionService.getScore(responses));
//    }
}
