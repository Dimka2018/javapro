package com.dimka.javapro.service.api;

import com.dimka.javapro.dto.ArticleLinkResponse;
import com.dimka.javapro.mapper.ArticleToArticleLinkResponseMapper;
import com.dimka.javapro.model.Article;
import com.dimka.javapro.service.ArticleService;
import com.dimka.javapro.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ArticleApiService {

    private final ArticleService articleService;
    private final ImageService imageService;

    private final ArticleToArticleLinkResponseMapper articleToArticleLinkDtoMapper;

    public List<ArticleLinkResponse> getArticles(@Nullable String text) {
        List<Article> articles;
        if (StringUtils.isNoneBlank(text)) {
            articles = articleService.searchByText(text);
        } else {
            articles = articleService.getAll();
        }
        return articleToArticleLinkDtoMapper.convert(articles);
    }

    public Article getArticle(String id) {
        return articleService.get(id);
    }

    public void deleteArticle(String id) {
        articleService.delete(id);
    }

    public Article createArticle() {
        return articleService.save(new Article());
    }

    public Article updateArticle(Article article) {
        imageService.deleteUnused(article);
        return articleService.save(article);
    }
}