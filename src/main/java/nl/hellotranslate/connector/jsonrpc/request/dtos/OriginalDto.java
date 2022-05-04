package nl.hellotranslate.connector.jsonrpc.request.dtos;

import nl.hellotranslate.connector.model.decorators.dtos.LanguageDto;
import nl.hellotranslate.connector.model.decorators.dtos.NameDto;

public record OriginalDto(NameDto name, LanguageDto language) {

}
