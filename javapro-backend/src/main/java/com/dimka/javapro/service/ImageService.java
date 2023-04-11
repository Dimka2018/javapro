package com.dimka.javapro.service;

import com.dimka.javapro.model.Image;
import com.jlefebure.spring.boot.minio.MinioService;
import io.minio.messages.Item;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.http.entity.ContentType;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class ImageService {

    private final MinioService imageRepository;

    @SneakyThrows
    public void save(Image image) {
        byte[] content = SerializationUtils.serialize(image);
        try (InputStream stream = new ByteArrayInputStream(content)) {
            imageRepository.upload(Path.of(image.getId()), stream, ContentType.DEFAULT_BINARY);
        }
    }

    @SneakyThrows
    public Image get(String id) {
        return SerializationUtils.deserialize(imageRepository.get(Path.of(id)).readAllBytes());
    }

    @SneakyThrows
    public List<Image> findAll() {
        return imageRepository.list().stream()
                .map(Item::objectName)
                .map(this::get)
                .collect(Collectors.toList());
    }

    @SneakyThrows
    public void delete(List<String> ids) {
        ids.forEach(this::delete);
    }

    @SneakyThrows
    private void delete(String id) {
        imageRepository.remove(Path.of(id));
    }

}
