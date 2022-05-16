package com.hellotranslate.connector.exception.jsonrpc.bodyvalidation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.OK)
public abstract sealed class RequestBodyValidationException
        extends RuntimeException
        permits InvalidConfigException, InvalidEntityException,
        InvalidMethodException, InvalidRequestBodyException, InvalidRequestParameters,
        InvalidScopeException, InvalidXdipException, NoContentToUploadException {

    private final int errorCode;

    protected RequestBodyValidationException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
