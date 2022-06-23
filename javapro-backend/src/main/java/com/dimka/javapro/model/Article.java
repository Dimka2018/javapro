package com.dimka.javapro.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@JsonInclude(JsonInclude.Include.USE_DEFAULTS)
@Accessors(chain = true)
@Data
@Document("ARTICLE")
public class Article {

    @Id
    private String id;
    private String title;
    private Object content;

}
