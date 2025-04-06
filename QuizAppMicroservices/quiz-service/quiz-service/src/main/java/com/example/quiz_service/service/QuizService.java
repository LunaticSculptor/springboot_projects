package com.example.quiz_service.service;


import com.example.quiz_service.fein.QuizInterface;
import com.example.quiz_service.model.QuestionWrapper;
import com.example.quiz_service.model.Quiz;
import com.example.quiz_service.model.Response;
import com.example.quiz_service.repository.QuizRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepo quizRepo;
//    private final QuestionRepo questionRepo;
    private final QuizInterface quizInterface;

    public String createQuiz(String category, int numQ, String title) {

        List<Integer> questions = quizInterface.getQuestionsForQuiz(category, numQ).getBody();

//        List<Question> questions = questionRepo.findRandomQuestionsByCategory(category, numQ);
//
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
//        quiz.setQuestions(questions);
        quiz.setQuestionIds(questions);

        quizRepo.save(quiz);
        return "Success";
    }

    public List<QuestionWrapper> getQuizQuestions(Integer id) {

        Optional<Quiz> quiz1 = quizRepo.findById(id);
        if (quiz1.isEmpty()) {
            throw new RuntimeException("Quiz not found with id: " + id);
        }
//        List<Question> questionFromDB = quiz.get().getQuestions();
        Quiz quiz = quiz1.get();
        List<Integer> questionIds = quiz.getQuestionIds();

        List<QuestionWrapper> questionForUser = new ArrayList<>();
        questionForUser = quizInterface.getQuestionsFromId(questionIds).getBody();
//
//        for(Question q: questionFromDB) {
//            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
//            questionForUser.add(qw);
//        }
        return questionForUser;
    }

    public Integer calculateResult(Integer id, List<Response> responses) {
//        Optional<Quiz> quiz1 = quizRepo.findById(id);
//        if (quiz1.isEmpty()) {
//            throw new RuntimeException("Quiz not found with id: " + id);
//        }
//        Quiz quiz = quiz1.get();
////        List<Question> questions = quiz.getQuestions();
////
//        int right = 0;
////        int i = 0;
////        for(Response response : responses){
////            if(response.getResponse().equals(questions.get(i).getRightAnswer()))
////                right++;
////            i++;
////        }
//        return right;

        return quizInterface.getScore(responses).getBody();
    }
}
