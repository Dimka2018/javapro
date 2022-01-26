package com.dimka.javapro.service;

import com.dimka.javapro.model.Article;
import com.dimka.javapro.model.Image;
import com.dimka.javapro.repository.ImageRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@RequiredArgsConstructor
@Service
public class ImageService {

    private final ImageRepository imageRepository;
    private final ObjectMapper objectMapper;

    public Image saveImage(String url, String articleId) {
        byte[] content = new RestTemplate().getForObject(url, byte[].class);
        Image image = new Image()
                .setContent(content)
                .setArticleId(articleId);
        return imageRepository.save(image);
    }

    public byte[] get(String id) {
        return imageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Image not found for id " + id))
                .getContent();
    }

    @Async
    @SneakyThrows
    public void deleteUnused(Article article) {
        String content = objectMapper.writeValueAsString(article.getContent());
        List<String> ids = StreamSupport.stream(imageRepository.findAll().spliterator(), false)
                .filter(image -> image.getArticleId().equals(article.getId()))
                .map(Image::getId)
                .filter(id -> !content.contains(id))
                .collect(Collectors.toList());
        log.info(String.valueOf(ids));
        imageRepository.deleteAllById(ids);
    }

}
