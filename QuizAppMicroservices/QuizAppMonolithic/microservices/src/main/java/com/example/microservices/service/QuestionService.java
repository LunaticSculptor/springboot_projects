package com.example.microservices.service;

import com.example.microservices.model.Question;
import com.example.microservices.model.QuestionWrapper;
import com.example.microservices.model.Response;
import com.example.microservices.repository.QuestionRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

//    public List<Integer> getQuestionsForQuiz(String categoryName, Integer numQuestions) {
//        List<Integer> questions = questionRepo.findRandomQuestionsByCategory(categoryName, numQuestions);
//        return questions;
//    }
//
//    public List<QuestionWrapper> getQuestionsFromId(List<Integer> questionIds) {
//        List<QuestionWrapper> wrappers = new ArrayList<>();
//        List<Question> questions = new ArrayList<>();
//
//        for(Integer id : questionIds){
//            questions.add(questionRepo.findById(id).get());
//        }
//
//        for(Question question : questions){
//            QuestionWrapper wrapper = new QuestionWrapper();
//            wrapper.setId(question.getId());
//            wrapper.setQuestionTitle(question.getQuestionTitle());
//            wrapper.setOption1(question.getOption1());
//            wrapper.setOption2(question.getOption2());
//            wrapper.setOption3(question.getOption3());
//            wrapper.setOption4(question.getOption4());
//            wrappers.add(wrapper);
//        }
//        return wrappers;
//    }
//
//    public Integer getScore(List<Response> responses) {
//
//        int right = 0;
//
//        for(Response response : responses){
//            Question question = questionRepo.findById(response.getId()).get();
//            if(response.getResponse().equals(question.getRightAnswer()))
//                right++;
//        }
//        return right;
//    }
}
