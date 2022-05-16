package com.hellotranslate.connector.exception.jsonrpc.bodyvalidation;

public final class InvalidEntityException extends RequestBodyValidationException {

    public InvalidEntityException(String message, int errorCode) {
        super(message, errorCode);
    }
}
