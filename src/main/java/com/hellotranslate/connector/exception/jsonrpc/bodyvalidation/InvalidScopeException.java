package com.hellotranslate.connector.exception.jsonrpc.bodyvalidation;

public final class InvalidScopeException extends RequestBodyValidationException {

    public InvalidScopeException(String message, int errorCode) {
        super(message, errorCode);
    }
}
