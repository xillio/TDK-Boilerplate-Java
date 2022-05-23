package com.hellotranslate.connector.jsonrpc.response;

import com.hellotranslate.connector.jsonrpc.response.dtos.Result;

public record ResultResponseBody(String id, String jsonrpc, Result result) implements ResponseBody {

}
