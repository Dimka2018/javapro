package com.dimka.javapro.mapper;

import com.dimka.javapro.dto.ArticleLinkResponse;
import com.dimka.javapro.model.Article;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArticleToArticleLinkResponseMapper {

    ArticleLinkResponse convert(Article article);

    List<ArticleLinkResponse> convert(List<Article> articles);
}
