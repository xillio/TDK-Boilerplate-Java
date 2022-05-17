package com.hellotranslate.connector.exception.jsonrpc.bodyvalidation;

import static com.hellotranslate.connector.jsonrpc.response.errors.JsonRpcErrors.PARSE_ERROR;

public final class InvalidXdipException extends RequestBodyValidationException {

    public InvalidXdipException(String message) {
        super(message, PARSE_ERROR.code());
    }
}
