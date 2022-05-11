package com.hellotranslate.connector.exception.jsonrpc.bodyvalidation;

public final class InvalidScopeException extends RequestBodyValidationException {


    public InvalidScopeException(String requestId, String message, int errorCode) {
        super(requestId, message, errorCode);
    }
}
