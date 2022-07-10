package com.dimka.javapro.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("QUESTION")
@Data
public class Question {

    @Id
    private String id;
    private String contact;
    private String text;
    private String ip;

}
