package com.hellotranslate.connector.exception.jsonrpc.response;

public final class ContentConversionException extends ResponseBodyException {

    public ContentConversionException(String requestId, String message, int errorCode) {
        super(requestId, message, errorCode);
    }
}
