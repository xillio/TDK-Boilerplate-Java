package com.hellotranslate.connector.exception.jsonrpc.response;

public final class OperationNotAllowedException extends ResponseBodyException {

    public OperationNotAllowedException(String message, int errorCode) {
        super(message, errorCode);
    }
}
