package com.dimka.javapro.controller;

import com.dimka.javapro.dto.ArticleLinkDto;
import com.dimka.javapro.model.Article;
import com.dimka.javapro.service.api.ArticleApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ArticleController {

    private final ArticleApiService articleApiService;

    @GetMapping("/articles")
    public List<ArticleLinkDto> getArticleList() {
        return articleApiService.getArticles();
    }

    @PostMapping("/article")
    public String createArticle() {
        return articleApiService.createArticle();
    }

    @PutMapping("/article")
    public Article updateArticle(Article article) {
        return articleApiService.updateArticle(article);
    }
}
