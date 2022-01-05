package com.dimka.javapro.repository;

import com.dimka.javapro.model.Article;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ArticleRepository extends MongoRepository<Article, String> {

    List<Article> findAllByTitleContainsIgnoreCase(String title);
}
