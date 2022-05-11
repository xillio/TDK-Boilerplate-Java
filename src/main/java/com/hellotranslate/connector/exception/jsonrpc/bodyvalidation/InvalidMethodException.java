package com.hellotranslate.connector.exception.jsonrpc.bodyvalidation;

public final class InvalidMethodException extends RequestBodyValidationException {


    public InvalidMethodException(String requestId, String message, int errorCode) {
        super(requestId, message, errorCode);
    }
}
