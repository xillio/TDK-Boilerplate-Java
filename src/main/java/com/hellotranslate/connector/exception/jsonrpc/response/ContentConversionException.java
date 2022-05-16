package com.hellotranslate.connector.exception.jsonrpc.response;

public final class ContentConversionException extends ResponseBodyException {

    public ContentConversionException(String message, int errorCode) {
        super(message, errorCode);
    }
}
