package com.dimka.javapro.model.component;

import com.dimka.javapro.model.content.Content;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

public class Paragraph extends Component {

    private Map<String, Object> attributes;
    private List<Content> content;

}
