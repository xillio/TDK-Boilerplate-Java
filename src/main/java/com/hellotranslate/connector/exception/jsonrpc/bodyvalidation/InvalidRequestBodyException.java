package com.hellotranslate.connector.exception.jsonrpc.bodyvalidation;

public final class InvalidRequestBodyException extends RequestBodyValidationException{


    public InvalidRequestBodyException(String requestId, String message, int errorCode) {
        super(requestId, message, errorCode);
    }
}
