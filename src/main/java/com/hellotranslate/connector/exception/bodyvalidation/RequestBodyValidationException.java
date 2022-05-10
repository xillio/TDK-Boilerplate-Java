package com.hellotranslate.connector.exception.bodyvalidation;

import java.io.InvalidObjectException;

public abstract class RequestBodyValidationException extends InvalidObjectException {

    private final int errorCode;

    protected RequestBodyValidationException(
            String message,
            int errorCode)
    {

        super(message);
        this.errorCode = errorCode;
    }

    protected RequestBodyValidationException(
            String message,
            Throwable cause,
            int errorCode)
    {
        super(String.format("%s cause: %s", message, cause));
        this.errorCode = errorCode;
    }

    public int getErrorCode()
    {
        return errorCode;
    }
}