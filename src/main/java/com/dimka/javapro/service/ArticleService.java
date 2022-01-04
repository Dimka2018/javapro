package com.dimka.javapro.service;

import com.dimka.javapro.model.Article;
import com.dimka.javapro.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public List<Article> getAll() {
        return articleRepository.findAll();
    }

    public Article save(Article article) {
        return articleRepository.save(article);
    }

    public Article get(String id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Article not found for id " + id));
    }

    public void delete(String id) {
        articleRepository.deleteById(id);
    }
}
