package com.hellotranslate.connector.exception.jsonrpc.bodyvalidation;

public final class InvalidMethodException extends RequestBodyValidationException {

    public InvalidMethodException(String message, int errorCode) {
        super(message, errorCode);
    }
}
