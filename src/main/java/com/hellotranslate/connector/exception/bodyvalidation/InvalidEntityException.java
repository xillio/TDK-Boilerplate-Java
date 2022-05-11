package com.hellotranslate.connector.exception.bodyvalidation;

public final class InvalidEntityException extends RequestBodyValidationException{

    public InvalidEntityException(String message, int errorCode)
    {
        super(message, errorCode);
    }

    public InvalidEntityException(
            String message,
            Throwable cause,
            int errorCode)
    {
        super(message, cause, errorCode);
    }
}
