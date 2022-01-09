package com.dimka.javapro.service;

import com.dimka.javapro.model.Question;
import com.dimka.javapro.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public void save(Question question) {
        questionRepository.save(question);
    }
}
