package com.hellotranslate.connector.exception.response;

public class NoContentDownloaded extends ResponseBodyException {

    public NoContentDownloaded(String message, int errorCode) {
        super(message, errorCode);
    }
}
