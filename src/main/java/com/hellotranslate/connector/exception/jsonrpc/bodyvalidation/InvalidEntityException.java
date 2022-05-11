package com.hellotranslate.connector.exception.jsonrpc.bodyvalidation;

public final class InvalidEntityException extends RequestBodyValidationException {

    public InvalidEntityException(String requestId, String message, int errorCode) {
        super(requestId, message, errorCode);
    }
}
