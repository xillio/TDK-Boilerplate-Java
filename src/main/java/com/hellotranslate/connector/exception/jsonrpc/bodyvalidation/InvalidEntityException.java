package com.hellotranslate.connector.exception.jsonrpc.bodyvalidation;

import static com.hellotranslate.connector.jsonrpc.response.errors.JsonRpcErrors.INVALID_PARAMS;

public final class InvalidEntityException extends RequestBodyValidationException {

    public InvalidEntityException(String message) {
        super(message, INVALID_PARAMS.code());
    }
}
