package com.hellotranslate.connector.exception.jsonrpc.bodyvalidation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.OK)
public abstract class RequestBodyValidationException extends RuntimeException {

    private final int errorCode;

    protected RequestBodyValidationException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
