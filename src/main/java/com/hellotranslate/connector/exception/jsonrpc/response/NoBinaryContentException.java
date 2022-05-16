package com.hellotranslate.connector.exception.jsonrpc.response;

public final class NoBinaryContentException extends ResponseBodyException {

    public NoBinaryContentException(String message, int errorCode) {
        super(message, errorCode);
    }
}
