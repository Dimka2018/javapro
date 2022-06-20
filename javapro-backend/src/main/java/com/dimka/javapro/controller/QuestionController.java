package com.dimka.javapro.controller;

import com.dimka.javapro.model.Question;
import com.dimka.javapro.service.api.QuestionApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class QuestionController {

    private final QuestionApiService questionApiService;

    @PostMapping("/question")
    public void saveQuestion(@RequestBody Question question) {
        questionApiService.save(question);
    }
}
