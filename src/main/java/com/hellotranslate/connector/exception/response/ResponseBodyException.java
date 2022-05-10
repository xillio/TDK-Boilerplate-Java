package com.hellotranslate.connector.exception.response;

public abstract class ResponseBodyException extends Exception {

    private final int errorCode;

    public ResponseBodyException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
