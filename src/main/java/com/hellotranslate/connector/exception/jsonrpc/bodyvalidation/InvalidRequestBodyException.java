package com.hellotranslate.connector.exception.jsonrpc.bodyvalidation;

public final class InvalidRequestBodyException extends RequestBodyValidationException {

    public InvalidRequestBodyException(String message, int errorCode) {
        super(message, errorCode);
    }
}
