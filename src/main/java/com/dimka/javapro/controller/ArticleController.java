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
    public List<ArticleLinkDto> getArticleList(@RequestParam(required = false) String text) {
        return articleApiService.getArticles(text);
    }

    @GetMapping("/articles/{id}")
    public Article getArticle(@PathVariable String id) {
        return articleApiService.getArticle(id);
    }

    @DeleteMapping("/articles/{id}")
    public void deleteArticle(@PathVariable String id) {
        articleApiService.deleteArticle(id);
    }

    @PostMapping("/article")
    public Article createArticle() {
        return articleApiService.createArticle();
    }

    @PutMapping("/article")
    public Article updateArticle(@RequestBody Article article) {
        return articleApiService.updateArticle(article);
    }

}
