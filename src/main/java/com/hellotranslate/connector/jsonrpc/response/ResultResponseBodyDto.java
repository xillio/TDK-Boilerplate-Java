package com.hellotranslate.connector.jsonrpc.response;

import com.hellotranslate.connector.jsonrpc.response.dtos.ResultDto;

public record ResultResponseBodyDto(
        String id,
        String jsonrpc,
        ResultDto result) implements ResponseBody {

}
