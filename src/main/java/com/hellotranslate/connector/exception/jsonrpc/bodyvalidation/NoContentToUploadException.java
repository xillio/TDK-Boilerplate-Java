package com.hellotranslate.connector.exception.jsonrpc.bodyvalidation;

public final class NoContentToUploadException extends RequestBodyValidationException {

    public NoContentToUploadException(String message, int errorCode) {
        super(message, errorCode);
    }
}
