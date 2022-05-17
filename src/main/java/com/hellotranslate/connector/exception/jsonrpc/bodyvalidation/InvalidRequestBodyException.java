package com.hellotranslate.connector.exception.jsonrpc.bodyvalidation;

import static com.hellotranslate.connector.jsonrpc.response.errors.JsonRpcErrors.INVALID_REQUEST;

public final class InvalidRequestBodyException extends RequestBodyValidationException {

    public InvalidRequestBodyException(String message) {
        super(message, INVALID_REQUEST.code());
    }
}
