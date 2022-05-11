package com.hellotranslate.connector.exception.bodyvalidation;

public abstract sealed class RequestBodyValidationException
        extends Exception
        permits InvalidConfigException, InvalidEntityException,
        InvalidMethodException, InvalidRequestBodyException, InvalidRequestParameters,
        InvalidScopeException, InvalidXdipException, NoContentToUploadException {

    private final int errorCode;

    protected RequestBodyValidationException(
            String message,
            int errorCode) {

        super(message);
        this.errorCode = errorCode;
    }

    protected RequestBodyValidationException(
            String message,
            Throwable cause,
            int errorCode) {
        super(String.format("%s cause: %s", message, cause));
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
