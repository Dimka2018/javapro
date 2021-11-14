package com.dimka.javapro.repository;

import com.dimka.javapro.model.Article;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ArticleRepository extends MongoRepository<Article, Long> {
}
