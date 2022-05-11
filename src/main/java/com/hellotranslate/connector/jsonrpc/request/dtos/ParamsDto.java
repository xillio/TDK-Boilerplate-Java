package com.hellotranslate.connector.jsonrpc.request.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hellotranslate.connector.jsonrpc.EntityDto;
import com.hellotranslate.connector.model.XDIP;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ParamsDto(Map<String, Object> config,
                        XDIP xdip,
                        RequestParametersDto requestParameters,
                        EntityDto entity,
                        String binaryContents) {

}
