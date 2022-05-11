package com.hellotranslate.connector.exception.jsonrpc.bodyvalidation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.OK)
public abstract sealed class RequestBodyValidationException
        extends RuntimeException
        permits InvalidConfigException, InvalidEntityException,
        InvalidMethodException, InvalidRequestBodyException, InvalidRequestParameters,
        InvalidScopeException, InvalidXdipException, NoContentToUploadException {

    private final String requestId;
    private final int errorCode;

    protected RequestBodyValidationException(String requestId, String message, int errorCode) {

        super(message);
        this.requestId = requestId;
        this.errorCode = errorCode;
    }

    public String getRequestId() {
        return requestId;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
