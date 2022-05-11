package com.hellotranslate.connector.jsonrpc.response.errors;

import com.hellotranslate.connector.exception.ClassInstantiationForbiddenException;

public final class JsonRpcErrors {

    public static final JsonRpcError PARSE_ERROR = new JsonRpcError(-32700, "Parse error");
    public static final JsonRpcError INVALID_REQUEST = new JsonRpcError(-32600, "Invalid Request");
    public static final JsonRpcError METHOD_NOT_FOUND = new JsonRpcError(-32601, "Method not found");
    public static final JsonRpcError INVALID_PARAMS = new JsonRpcError(-32602, "Invalid params");
    public static final JsonRpcError INTERNAL_ERROR = new JsonRpcError(-32603, "Internal error");

    private JsonRpcErrors() throws ClassInstantiationForbiddenException {
        throw new ClassInstantiationForbiddenException(JsonRpcErrors.class);
    }
}
