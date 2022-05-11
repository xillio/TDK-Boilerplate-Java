package com.hellotranslate.connector.exception.bodyvalidation;

public final class InvalidScopeException extends RequestBodyValidationException {

    public InvalidScopeException(
            String message,
            int errorCode)
    {
        super(message, errorCode);
    }

    public InvalidScopeException(
            String message,
            Throwable cause,
            int errorCode)
    {
        super(message, cause, errorCode);
    }
}
