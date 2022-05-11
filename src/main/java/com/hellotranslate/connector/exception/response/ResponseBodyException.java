package com.hellotranslate.connector.exception.response;

public abstract sealed class ResponseBodyException
        extends Exception
        permits ContentDownloadingFailedException {

    private final int errorCode;

    protected ResponseBodyException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
