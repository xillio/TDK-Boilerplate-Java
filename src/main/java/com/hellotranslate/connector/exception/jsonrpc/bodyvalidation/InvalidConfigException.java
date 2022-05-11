package com.hellotranslate.connector.exception.jsonrpc.bodyvalidation;

public final class InvalidConfigException extends RequestBodyValidationException {

    public InvalidConfigException(String requestId, String message, int errorCode) {
        super(requestId, message, errorCode);
    }
}
