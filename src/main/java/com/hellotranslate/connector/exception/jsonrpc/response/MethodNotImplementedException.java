package com.hellotranslate.connector.exception.jsonrpc.response;

public final class MethodNotImplementedException extends ResponseBodyException {

    private static final String DEFAULT_MESSAGE = "Method not implemented";

    public MethodNotImplementedException(int errorCode) {
        super(DEFAULT_MESSAGE, errorCode);
    }
}
