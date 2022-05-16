package com.hellotranslate.connector.exception.jsonrpc.bodyvalidation;

public final class InvalidXdipException extends RequestBodyValidationException {

    public InvalidXdipException(String message, int errorCode) {
        super(message, errorCode);
    }
}
