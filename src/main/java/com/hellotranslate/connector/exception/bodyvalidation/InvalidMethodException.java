package com.hellotranslate.connector.exception.bodyvalidation;

public class InvalidMethodException extends RequestBodyValidationException {

    public InvalidMethodException(
            String message,
            int errorCode)
    {
        super(message, errorCode);
    }

    public InvalidMethodException(
            String message,
            Throwable cause,
            int errorCode)
    {
        super(message, cause, errorCode);
    }
}
