package com.hellotranslate.connector.exception.jsonrpc.response;

public final class ContentDownloadingFailedException extends ResponseBodyException {

    public ContentDownloadingFailedException(String requestId, String message, int errorCode) {
        super(requestId, message, errorCode);
    }
}
