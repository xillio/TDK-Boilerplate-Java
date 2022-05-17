package com.hellotranslate.connector.exception.jsonrpc.response;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.OK)
public abstract class ResponseBodyException extends RuntimeException {

    private final int errorCode;

    protected ResponseBodyException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
