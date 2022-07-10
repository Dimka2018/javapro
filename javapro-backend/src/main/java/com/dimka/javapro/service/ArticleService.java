package com.dimka.javapro.service;

import com.dimka.javapro.model.Article;
import com.dimka.javapro.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<Article> searchByText(String text) {
        List<Article> titleResult = articleRepository.findAllByTagsContainsIgnoreCase(text);
        List<Article> tagsResult = articleRepository.findAllByTagsContainsIgnoreCase(text);
        return List.of(titleResult, tagsResult).stream()
                .flatMap(List::stream)
                .distinct()
                .collect(Collectors.toList());
    }
}
