package com.hellotranslate.connector.jsonrpc.response;

import com.hellotranslate.connector.jsonrpc.response.dtos.ErrorDto;

public record ErrorResponseBodyDto(String id,
                                   String jsonrpc,
                                   ErrorDto error) implements ResponseBody {

}
