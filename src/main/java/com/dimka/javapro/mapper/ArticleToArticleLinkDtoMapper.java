package com.dimka.javapro.mapper;

import com.dimka.javapro.dto.ArticleLinkDto;
import com.dimka.javapro.model.Article;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArticleToArticleLinkDtoMapper {

    ArticleLinkDto convert(Article article);

    List<ArticleLinkDto> convert(List<Article> articles);
}
