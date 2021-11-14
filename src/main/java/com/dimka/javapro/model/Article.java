package com.dimka.javapro.model;

import com.dimka.javapro.model.component.Component;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;

@Data
@Document("ARTICLE")
public class Article {

    @Id
    private String id;
    private String title;
    private List<Component> components;

}
