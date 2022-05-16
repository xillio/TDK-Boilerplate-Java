package com.hellotranslate.connector.exception.jsonrpc.response;

public final class ContentDownloadingFailedException extends ResponseBodyException {

    public ContentDownloadingFailedException(String message, int errorCode) {
        super(message, errorCode);
    }
}
