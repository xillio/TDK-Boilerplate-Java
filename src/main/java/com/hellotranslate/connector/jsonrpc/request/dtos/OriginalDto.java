package com.hellotranslate.connector.jsonrpc.request.dtos;

import com.hellotranslate.connector.model.decorators.Language;
import com.hellotranslate.connector.model.decorators.Name;

public record OriginalDto(Name name, Language language) {

}
