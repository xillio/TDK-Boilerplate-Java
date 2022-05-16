package com.hellotranslate.connector.exception.jsonrpc.bodyvalidation;

public final class InvalidConfigException extends RequestBodyValidationException {

    public InvalidConfigException(String message, int errorCode) {
        super(message, errorCode);
    }
}
