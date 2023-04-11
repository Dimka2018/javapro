package com.dimka.javapro.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.USE_DEFAULTS)
@Accessors(chain = true)
@Data
public class Image implements Serializable {

    private String id;
    private String articleId;
    private byte[] content;

}
