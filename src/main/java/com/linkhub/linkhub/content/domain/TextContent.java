package com.linkhub.linkhub.content.domain;

import lombok.Getter;

@Getter
public class TextContent implements PostContent{

    private String text;

    private TextContent(String text) {
        this.text = text;
    }

    public static TextContent create (String text) {
        if (text == null ||
                text.isBlank()) {
            throw new IllegalArgumentException("Text must not be blank");
        }
        if (text.length() > 4000) {
            throw new IllegalArgumentException("Length of the text bigger than 4000 symbols");
        }

        return new TextContent(text);
    }
}
