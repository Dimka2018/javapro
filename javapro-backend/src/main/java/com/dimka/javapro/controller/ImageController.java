package com.dimka.javapro.controller;

import com.dimka.javapro.dto.ImageRequest;
import com.dimka.javapro.dto.ImageResponse;
import com.dimka.javapro.service.api.ImageApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class ImageController {

    private final ImageApiService imageApiService;

    @PostMapping("/image")
    public ImageResponse saveImage(@RequestBody ImageRequest request) {
        return imageApiService.saveImage(request);
    }

    @GetMapping("/images/{id}")
    public byte[] getImage(@PathVariable String id) {
        return imageApiService.getImage(id);
    }
}
