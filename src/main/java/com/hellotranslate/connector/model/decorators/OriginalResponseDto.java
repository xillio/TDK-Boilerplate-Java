package com.hellotranslate.connector.model.decorators;

public record OriginalResponseDto(ContentType contentType,
                                  Created created,
                                  Language language,
                                  MimeType mimeType,
                                  File file,
                                  Modified modified,
                                  Name name,
                                  Parent parent) {
}
