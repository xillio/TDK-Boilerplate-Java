package com.hellotranslate.connector.jsonrpc.request;

import com.hellotranslate.connector.jsonrpc.request.dtos.ParamsDto;

import java.io.Serializable;

public record RequestDto(
        String id,
        String jsonrpc,
        String method,
        ParamsDto params) implements Serializable {

}
