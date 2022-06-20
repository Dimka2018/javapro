package com.dimka.javapro.repository;

import com.dimka.javapro.model.Article;
import com.dimka.javapro.model.Question;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuestionRepository extends MongoRepository<Question, String> {
}
