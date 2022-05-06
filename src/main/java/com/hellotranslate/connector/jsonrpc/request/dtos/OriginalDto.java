package com.hellotranslate.connector.jsonrpc.request.dtos;

import com.hellotranslate.connector.model.decorators.dtos.LanguageDto;
import com.hellotranslate.connector.model.decorators.dtos.NameDto;

public record OriginalDto(NameDto name, LanguageDto language) {

}
