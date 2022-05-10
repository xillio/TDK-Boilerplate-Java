package com.hellotranslate.connector.jsonrpc.response;

public record ResultResponseBodyDto(
        String id,
        String jsonrpc,
        ResultDto result) implements ResponseBody {

}
