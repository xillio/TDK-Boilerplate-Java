package com.hellotranslate.connector.jsonrpc.response;

import com.hellotranslate.connector.jsonrpc.response.dtos.Error;

public record ErrorResponseBody(String id, String jsonrpc, Error error) implements ResponseBody {

}
