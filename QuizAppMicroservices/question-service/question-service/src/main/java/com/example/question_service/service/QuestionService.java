package com.example.question_service.service;


import com.example.question_service.model.Question;
import com.example.question_service.model.QuestionWrapper;
import com.example.question_service.model.Response;
import com.example.question_service.repository.QuestionRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class QuestionService {

    private QuestionRepo questionRepo;

    public List<Question> getAllQuestions() {
        return questionRepo.findAll();
    }

    public Question addQuestion(Question question) {
        return questionRepo.save(question);
    }

    public List<Question> getQuestionsByCategory(String categoryName) {
        return questionRepo.findByCategory(categoryName);
    }

    public List<Integer> getQuestionsForQuiz(String categoryName, Integer numQuestions) {
        List<Integer> questions = questionRepo.findRandomQuestionsByCategory(categoryName, numQuestions);
        return questions;
    }

    public List<QuestionWrapper> getQuestionsFromId(List<Integer> questionIds) {
        List<QuestionWrapper> wrappers = new ArrayList<>();
        List<Question> questions = new ArrayList<>();

        for(Integer id : questionIds){
            Question question = questionRepo.findById(id)
                    .orElseThrow(() -> new NoSuchElementException("Question not found with id: " + id));
            questions.add(question);
        }


        for(Question question : questions){
            QuestionWrapper wrapper = new QuestionWrapper();
            wrapper.setId(question.getId());
            wrapper.setQuestionTitle(question.getQuestionTitle());
            wrapper.setOption1(question.getOption1());
            wrapper.setOption2(question.getOption2());
            wrapper.setOption3(question.getOption3());
            wrapper.setOption4(question.getOption4());
            wrappers.add(wrapper);
        }

        return wrappers;
    }

    public Integer getScore(List<Response> responses) {


        int right = 0;

        for(Response response : responses){
            Question question = questionRepo.findById(response.getId()).get();
            if(response.getResponse().equals(question.getRightAnswer()))
                right++;
        }
        return right;
    }
}
