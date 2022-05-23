package com.hellotranslate.connector.model.decorators;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record LanguageDecorator(String tag, String translationOf) {

}
