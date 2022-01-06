package com.dimka.javapro.mapper;

import com.dimka.javapro.dto.ImageResponse;
import com.dimka.javapro.model.Image;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImageToImageResponseMapper {

    ImageResponse convert(Image image);
}
