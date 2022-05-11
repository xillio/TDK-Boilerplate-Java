package com.hellotranslate.connector.jsonrpc.request.dtos;

import com.hellotranslate.connector.jsonrpc.EntityDto;
import com.hellotranslate.connector.model.XDIP;

import java.util.Map;

public record ParamsDto(Map<String, Object> config,
                        XDIP xdip,
                        RequestParametersDto requestParameters,
                        EntityDto entity,
                        String binaryContents) {

}
