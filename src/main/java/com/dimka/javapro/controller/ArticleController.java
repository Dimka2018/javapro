package com.dimka.javapro.controller;

import com.dimka.javapro.dto.ArticleLinkDto;
import com.dimka.javapro.model.Article;
import com.dimka.javapro.service.api.ArticleApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class ArticleController {

    private final ArticleApiService articleApiService;

    @GetMapping("/articles")
    public List<ArticleLinkDto> getArticleList() {
        return articleApiService.getArticles();
    }

    @PostMapping("/article")
    public Article createArticle() {
        return articleApiService.createArticle();
    }

    @PutMapping("/article")
    public Article updateArticle(Article article) {
        return articleApiService.updateArticle(article);
    }
}
