package com.example.microservices.service;

import com.example.microservices.model.Question;
import com.example.microservices.model.QuestionWrapper;
import com.example.microservices.model.Quiz;
import com.example.microservices.model.Response;
import com.example.microservices.repository.QuestionRepo;
import com.example.microservices.repository.QuizRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepo quizRepo;
    private final QuestionRepo questionRepo;

    public String createQuiz(String category, int numQ, String title) {
        List<Question> questions = questionRepo.findRandomQuestionsByCategory(category, numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);

        quizRepo.save(quiz);
        return "Success";
    }

    public List<QuestionWrapper> getQuizQuestions(Integer id) {

        Optional<Quiz> quiz = quizRepo.findById(id);
        if (quiz.isEmpty()) {
            throw new RuntimeException("Quiz not found with id: " + id);
        }
        List<Question> questionFromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionForUser = new ArrayList<>();

        for(Question q: questionFromDB) {
            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
            questionForUser.add(qw);
        }
        return questionForUser;
    }

    public Integer calculateResult(Integer id, List<Response> responses) {
        Optional<Quiz> quiz1 = quizRepo.findById(id);
        if (quiz1.isEmpty()) {
            throw new RuntimeException("Quiz not found with id: " + id);
        }
        Quiz quiz = quiz1.get();
        List<Question> questions = quiz.getQuestions();

        int right = 0;
        int i = 0;
        for(Response response : responses){
            if(response.getResponse().equals(questions.get(i).getRightAnswer()))
                right++;
            i++;
        }
        return right;
    }
}
