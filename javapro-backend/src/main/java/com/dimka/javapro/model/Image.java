package com.dimka.javapro.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.redis.core.RedisHash;

@JsonInclude(JsonInclude.Include.USE_DEFAULTS)
@RedisHash("IMAGE")
@Accessors(chain = true)
@Data
public class Image {

    private String id;
    private String articleId;
    private byte[] content;

}
