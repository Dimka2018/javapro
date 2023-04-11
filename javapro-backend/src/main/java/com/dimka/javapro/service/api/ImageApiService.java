package com.dimka.javapro.service.api;

import com.dimka.javapro.dto.ImageRequest;
import com.dimka.javapro.dto.ImageResponse;
import com.dimka.javapro.mapper.ImageToImageResponseMapper;
import com.dimka.javapro.model.Image;
import com.dimka.javapro.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ImageApiService {

    private final ImageService imageService;
    private final ImageToImageResponseMapper imageToImageResponseMapper;

    public ImageResponse saveImage(ImageRequest imageRequest) {
        byte[] content = new RestTemplate().getForObject(imageRequest.getSrc(), byte[].class);
        String id = UUID.randomUUID().toString();
        Image image = new Image()
                .setContent(content)
                .setArticleId(id);
        imageService.save(image);
        return imageToImageResponseMapper.convert(image);
    }

    public byte[] getImage(String id) {
        return imageService.get(id).getContent();
    }

}
