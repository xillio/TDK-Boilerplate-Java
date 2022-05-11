package com.hellotranslate.connector.exception.jsonrpc.response;

public final class OperationNotAllowedException extends ResponseBodyException {

    public OperationNotAllowedException(String requestId, String message, int errorCode) {
        super(requestId, message, errorCode);
    }
}
