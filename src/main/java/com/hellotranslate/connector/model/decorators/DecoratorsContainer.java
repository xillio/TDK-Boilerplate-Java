package com.hellotranslate.connector.model.decorators;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.hellotranslate.connector.model.decorators.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public record DecoratorsContainer(ContainerDecorator container,
                                  ContentTypeDecorator contentType,
                                  CreatedDecorator created,
                                  FileDecorator file,
                                  LanguageDecorator language,
                                  MimeTypeDecorator mimeType,
                                  ModifiedDecorator modified,
                                  NameDecorator name,
                                  ParentDecorator parent) {
}
