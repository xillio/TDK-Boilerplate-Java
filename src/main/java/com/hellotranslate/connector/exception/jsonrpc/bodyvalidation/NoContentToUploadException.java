package com.hellotranslate.connector.exception.jsonrpc.bodyvalidation;

public final class NoContentToUploadException extends RequestBodyValidationException{

    public NoContentToUploadException(String requestId, String message, int errorCode) {
        super(requestId, message, errorCode);
    }
}
