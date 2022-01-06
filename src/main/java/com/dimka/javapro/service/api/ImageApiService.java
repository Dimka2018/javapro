package com.dimka.javapro.service.api;

import com.dimka.javapro.dto.ImageRequest;
import com.dimka.javapro.dto.ImageResponse;
import com.dimka.javapro.mapper.ImageToImageResponseMapper;
import com.dimka.javapro.repository.ImageRepository;
import com.dimka.javapro.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ImageApiService {

    private final ImageService imageService;
    private final ImageToImageResponseMapper imageToImageResponseMapper;

    public ImageResponse saveImage(ImageRequest imageRequest) {
        return imageToImageResponseMapper.convert(imageService.saveImage(imageRequest.getSrc()));
    }

    public byte[] getImage(String id) {
        return imageService.get(id);
    }

}
