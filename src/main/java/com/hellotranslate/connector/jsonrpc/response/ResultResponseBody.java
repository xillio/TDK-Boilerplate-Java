package com.hellotranslate.connector.jsonrpc.response;

public record ResultResponseBody(String id, String jsonrpc, Object result) implements ResponseBody {

}
