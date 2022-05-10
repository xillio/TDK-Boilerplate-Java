package com.hellotranslate.connector.jsonrpc.response;

public record ErrorResponseBodyDto(String id,
                                   String jsonrpc,
                                   ErrorDto error) implements ResponseBody {

}
