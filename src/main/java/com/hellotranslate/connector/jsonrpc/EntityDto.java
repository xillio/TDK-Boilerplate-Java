package com.hellotranslate.connector.jsonrpc;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.hellotranslate.connector.model.XDIP;
import com.hellotranslate.connector.model.decorators.OriginalDto;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public record EntityDto(XDIP id, String kind, OriginalDto original) {
}
