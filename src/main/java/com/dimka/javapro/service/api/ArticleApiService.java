package com.dimka.javapro.service.api;

import com.dimka.javapro.dto.ArticleLinkDto;
import com.dimka.javapro.mapper.ArticleToArticleLinkDtoMapper;
import com.dimka.javapro.model.Article;
import com.dimka.javapro.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ArticleApiService {

    private final ArticleService articleService;

    private final ArticleToArticleLinkDtoMapper articleToArticleLinkDtoMapper;

    public List<ArticleLinkDto> getArticles() {
        return articleToArticleLinkDtoMapper.convert(articleService.getAll());
    }

    public String createArticle() {
        return articleService.save(new Article()).getId();
    }

    public Article updateArticle(Article article) {
        return articleService.save(article);
    }
}