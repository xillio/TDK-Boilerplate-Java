package com.hellotranslate.connector.exception.jsonrpc.response;

/**
 * AuthorizationFailedException
 */
//todo extend documentation
public final class AuthorizationFailedException extends ResponseBodyException {

    public AuthorizationFailedException(String requestId, String message, int errorCode) {
        super(requestId, message, errorCode);
    }
}
