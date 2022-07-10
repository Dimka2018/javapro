package com.dimka.javapro.service.api;

import com.dimka.javapro.model.Question;
import com.dimka.javapro.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class QuestionApiService {

    private final QuestionService questionService;

    public void save(Question question, String ip) {
        question.setIp(ip);
        questionService.save(question);
    }

    public List<Question> getQuestions() {
        return questionService.findAll();
    }

    public void deleteQuestion(String id) {
        questionService.deleteQuestion(id);
    }
}
