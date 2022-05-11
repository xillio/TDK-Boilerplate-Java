package com.hellotranslate.connector.exception.bodyvalidation;

public final class InvalidRequestParameters extends RequestBodyValidationException {

    public InvalidRequestParameters(String message, int errorCode)
    {
        super(message, errorCode);
    }

    public InvalidRequestParameters(
            String message,
            Throwable cause,
            int errorCode)
    {
        super(message, cause, errorCode);
    }
}
