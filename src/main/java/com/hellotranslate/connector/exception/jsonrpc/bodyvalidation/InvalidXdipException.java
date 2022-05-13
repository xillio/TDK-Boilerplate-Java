package com.hellotranslate.connector.exception.jsonrpc.bodyvalidation;

public final class InvalidXdipException extends RequestBodyValidationException {

    public InvalidXdipException(
            String requestId,
            String message,
            int errorCode) {
        super(requestId, message, errorCode);
    }

    public InvalidXdipException(String message, int errorCode) {
        super(null, message, errorCode);
    }
}
