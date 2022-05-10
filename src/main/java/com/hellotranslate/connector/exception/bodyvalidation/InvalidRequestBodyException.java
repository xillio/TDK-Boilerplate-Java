package com.hellotranslate.connector.exception.bodyvalidation;

public class InvalidRequestBodyException extends RequestBodyValidationException{

    public InvalidRequestBodyException(String message, int errorCode)
    {
        super(message, errorCode);
    }

    public InvalidRequestBodyException(
            String message,
            Throwable cause,
            int errorCode)
    {
        super(message, cause, errorCode);
    }
}
