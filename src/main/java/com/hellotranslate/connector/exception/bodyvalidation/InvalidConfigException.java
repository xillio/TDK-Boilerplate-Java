package com.hellotranslate.connector.exception.bodyvalidation;

public class InvalidConfigException extends RequestBodyValidationException{

    public InvalidConfigException(String message, int errorCode)
    {
        super(message, errorCode);
    }

    public InvalidConfigException(
            String message,
            Throwable cause,
            int errorCode)
    {
        super(message, cause, errorCode);
    }
}
