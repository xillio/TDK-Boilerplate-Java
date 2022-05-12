package com.hellotranslate.connector.jsonrpc;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.hellotranslate.connector.decorators.OriginalDto;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public record EntityDto(String id, String kind, OriginalDto original) {
}
