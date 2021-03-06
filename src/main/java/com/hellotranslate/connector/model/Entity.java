package com.hellotranslate.connector.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.hellotranslate.connector.model.decorators.DecoratorsContainer;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public record Entity(XDIP id, XDIP xdip, String kind, DecoratorsContainer original, DecoratorsContainer modified) {
}
