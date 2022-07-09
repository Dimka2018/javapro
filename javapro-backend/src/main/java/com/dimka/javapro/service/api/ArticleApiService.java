package com.dimka.javapro.service.api;

import com.dimka.javapro.dto.ArticleLinkResponse;
import com.dimka.javapro.mapper.ArticleToArticleLinkResponseMapper;
import com.dimka.javapro.model.Article;
import com.dimka.javapro.model.Image;
import com.dimka.javapro.service.ArticleService;
import com.dimka.javapro.service.ImageService;
import com.dimka.javapro.utils.TextUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

@Slf4j
@RequiredArgsConstructor
@Service
public class ArticleApiService {

    private final ArticleService articleService;
    private final ImageService imageService;

    private final ArticleToArticleLinkResponseMapper articleToArticleLinkDtoMapper;

    public List<ArticleLinkResponse> getArticles(@Nullable String title) {
        List<Article> articles;
        if (StringUtils.isNoneBlank(title)) {
            articles = TextUtils.transform(title).stream()
                    .map(articleService::searchByText)
                    .flatMap(List::stream)
                    .distinct()
                    .collect(Collectors.toList());
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
        return articleService.save(article);
    }

    @SneakyThrows
    public byte[] getZipHistory() {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            try (ZipOutputStream zipOutputStream = new ZipOutputStream(bos)) {
                List<Article> articles = articleService.getAll();
                zipOutputStream.putNextEntry(new ZipEntry("articles.json"));
                zipOutputStream.write(new ObjectMapper().writeValueAsBytes(articles));
                zipOutputStream.closeEntry();

                imageService.findAll()
                        .forEach(image -> {
                            try {
                                zipOutputStream.putNextEntry(new ZipEntry(image.getId() + ".json"));
                                zipOutputStream.write(new ObjectMapper().writeValueAsBytes(image));
                                zipOutputStream.closeEntry();
                            } catch (IOException e) {
                                log.error("", e);
                            }
                        });
            }
            return bos.toByteArray();
        }
    }

    @SneakyThrows
    public void applyHistory(MultipartFile file) {
        ZipInputStream zipInputStream = new ZipInputStream(file.getInputStream());
        ArrayList<ZipEntry> entries = new ArrayList<>();
        ZipEntry zipEntry;
        while ((zipEntry = zipInputStream.getNextEntry()) != null) {
            entries.add(zipEntry);
        }

        File tempFile = File.createTempFile("upload", null);
        file.transferTo(tempFile);
        ZipFile zipFile = new ZipFile(tempFile);

        for (ZipEntry entry : entries) {
            byte[] buffer = zipFile.getInputStream(entry).readAllBytes();
            if (entry.getName().equals("articles.json")) {
                Article[] articles = toArticleList(buffer);
                Arrays.stream(articles).forEach(articleService::save);
            } else {
                Image image = new ObjectMapper()
                        .readValue(buffer, Image.class);
                imageService.save(image);
            }
        }
        tempFile.delete();
        zipInputStream.close();
    }

    @SneakyThrows
    private Article[] toArticleList(byte[] content) {
        return new ObjectMapper().readValue(content, Article[].class);
    }
}