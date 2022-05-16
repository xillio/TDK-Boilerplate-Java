package com.hellotranslate.connector.exception.jsonrpc.bodyvalidation;

public final class InvalidRequestParameters extends RequestBodyValidationException {

    public InvalidRequestParameters(String message, int errorCode) {
        super(message, errorCode);
    }
}
