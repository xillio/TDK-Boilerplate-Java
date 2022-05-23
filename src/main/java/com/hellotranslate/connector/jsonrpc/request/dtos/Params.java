package com.hellotranslate.connector.jsonrpc.request.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hellotranslate.connector.model.XDIP;
import com.hellotranslate.connector.model.Entity;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record Params(Map<String, Object> config,
                     XDIP xdip,
                     RequestParameters requestParameters,
                     Entity entity,
                     String binaryContents) {

}
