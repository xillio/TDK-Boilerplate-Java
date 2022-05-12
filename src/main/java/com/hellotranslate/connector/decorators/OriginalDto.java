package com.hellotranslate.connector.decorators;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public record OriginalDto(ContentType contentType,
                          Created created,
                          Language language,
                          MimeType mimeType,
                          File file,
                          Modified modified,
                          Name name,
                          Parent parent) {
}
