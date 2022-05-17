package com.hellotranslate.connector.exception.jsonrpc.bodyvalidation;

import static com.hellotranslate.connector.jsonrpc.response.errors.JsonRpcErrors.METHOD_NOT_FOUND;

public final class InvalidMethodException extends RequestBodyValidationException {

    public InvalidMethodException(String message) {
        super(message, METHOD_NOT_FOUND.code());
    }
}
