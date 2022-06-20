package com.dimka.javapro.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("ARTICLE")
public class Article {

    @Id
    private String id;
    private String title;
    private Object content;

}
