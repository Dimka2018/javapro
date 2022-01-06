package com.dimka.javapro.service;

import com.dimka.javapro.model.Image;
import com.dimka.javapro.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
public class ImageService {

    private final ImageRepository imageRepository;

    public Image saveImage(String url) {
        byte[] content = new RestTemplate().getForObject(url, byte[].class);
        Image image = new Image()
                .setContent(content);
        return imageRepository.save(image);
    }

    public byte[] get(String id) {
        return imageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Image not found for id " + id))
                .getContent();
    }

}
