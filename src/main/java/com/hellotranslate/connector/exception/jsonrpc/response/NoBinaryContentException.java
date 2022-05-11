package com.hellotranslate.connector.exception.jsonrpc.response;

public final class NoBinaryContentException extends ResponseBodyException {

    public NoBinaryContentException(String requestId, String message, int errorCode) {
        super(requestId, message, errorCode);
    }
}
