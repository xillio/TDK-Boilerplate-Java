package com.hellotranslate.connector.jsonrpc;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.hellotranslate.connector.decorators.Modified;
import com.hellotranslate.connector.decorators.Original;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public record EntityDto(String id, String xdip, String kind, Original original, Modified modified){
}
