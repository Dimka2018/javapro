package com.dimka.javapro.scheduler;

import com.dimka.javapro.model.Article;
import com.dimka.javapro.model.Image;
import com.dimka.javapro.service.ArticleService;
import com.dimka.javapro.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class ImageDeleteScheduler {

    private final ArticleService articleService;
    private final ImageService imageService;

    @Scheduled(fixedRate = 60000)
    public void deleteUnused() {
        List<String> ids = articleService.getAll()
                .stream()
                .map(Article::getId)
                .collect(Collectors.toList());
        List<String> imageIds = imageService.findAll()
                .filter(image -> !ids.contains(image.getArticleId()))
                .map(Image::getId)
                .collect(Collectors.toList());
        imageService.delete(imageIds);
    }
}
