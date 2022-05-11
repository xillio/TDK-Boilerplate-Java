package com.hellotranslate.connector.exception.bodyvalidation;

public final class NoContentToUploadException extends RequestBodyValidationException{

    public NoContentToUploadException(String message, int errorCode)
    {
        super(message, errorCode);
    }

    public NoContentToUploadException(
            String message,
            Throwable cause,
            int errorCode)
    {
        super(message, cause, errorCode);
    }
}
