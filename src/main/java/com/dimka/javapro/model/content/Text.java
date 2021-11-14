package com.dimka.javapro.model.content;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Text extends Content {

    private Map<String, Object> attributes;
    private List<Content> content;
}
