package com.hellotranslate.connector.exception.jsonrpc.bodyvalidation;

public final class InvalidRequestParameters extends RequestBodyValidationException {


    public InvalidRequestParameters(String requestId, String message, int errorCode) {
        super(requestId, message, errorCode);
    }
}
