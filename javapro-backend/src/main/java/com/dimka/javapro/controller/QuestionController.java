package com.dimka.javapro.controller;

import com.dimka.javapro.model.Question;
import com.dimka.javapro.service.api.QuestionApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class QuestionController {

    private final QuestionApiService questionApiService;

    @PostMapping("/question")
    public void saveQuestion(@RequestBody Question question, HttpServletRequest request) {
        questionApiService.save(question, request.getRemoteAddr());
    }

    @GetMapping("/questions")
    public List<Question> getQuestions() {
        return questionApiService.getQuestions();
    }

    @DeleteMapping("/questions/{id}")
    public void deleteQuestion(@PathVariable String id) {
        questionApiService.deleteQuestion(id);
    }
}
