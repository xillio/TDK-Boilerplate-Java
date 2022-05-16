package com.hellotranslate.connector.exception.jsonrpc.response;

public final class NoSuchEntityException extends ResponseBodyException {

    public NoSuchEntityException(String message, int errorCode) {
        super(message, errorCode);
    }
}
