package com.dimka.javapro.service.api;

import com.dimka.javapro.dto.ArticleLinkDto;
import com.dimka.javapro.mapper.ArticleToArticleLinkDtoMapper;
import com.dimka.javapro.model.Article;
import com.dimka.javapro.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ArticleApiService {

    private final ArticleService articleService;

    private final ArticleToArticleLinkDtoMapper articleToArticleLinkDtoMapper;

    public List<ArticleLinkDto> getArticles() {
        List<Article> articles = articleService.getAll().stream()
                .filter(article -> article.getTitle() != null)
                .collect(Collectors.toList());
        return articleToArticleLinkDtoMapper.convert(articles);
    }

    public Article createArticle() {
        return articleService.save(new Article());
    }

    public Article updateArticle(Article article) {
        return articleService.save(article);
    }
}