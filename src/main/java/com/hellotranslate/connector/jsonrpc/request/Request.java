package com.hellotranslate.connector.jsonrpc.request;

import com.hellotranslate.connector.jsonrpc.request.dtos.Params;

import java.io.Serializable;

public record Request(
        String id,
        String jsonrpc,
        String method,
        Params params) implements Serializable {

}
