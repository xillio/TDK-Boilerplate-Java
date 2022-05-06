package com.hellotranslate.connector.jsonrpc.request;

import com.hellotranslate.connector.jsonrpc.request.dtos.ParamsDto;

public record RequestDto(
        String id,
        String jsonrpc,
        String method,
        ParamsDto params) {

}
