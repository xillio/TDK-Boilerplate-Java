package com.hellotranslate.connector.exception.jsonrpc.response;

public final class NoSuchEntityException extends ResponseBodyException {

    public NoSuchEntityException(String requestId, String message, int errorCode) {
        super(requestId, message, errorCode);
    }
}
