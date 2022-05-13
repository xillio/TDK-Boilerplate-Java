package com.hellotranslate.connector.jsonrpc;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.hellotranslate.connector.decorators.Original;
import com.hellotranslate.connector.jsonrpc.request.XDIP;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public record EntityDto(String id, XDIP xdip, String kind, Original original, Original modified) {
}
