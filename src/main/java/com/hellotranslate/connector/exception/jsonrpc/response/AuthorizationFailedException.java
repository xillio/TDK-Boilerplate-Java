package com.hellotranslate.connector.exception.jsonrpc.response;

import com.hellotranslate.connector.service.ConfigValidationService;

/**
 * AuthorizationFailedException is optional to throw.
 * <p>
 * User implementing config validation at {@link ConfigValidationService} should decide
 * if they are going to throw this exception based on
 * if they would like to accept only authorized requests.
 * <p>
 * If the private method configIsInvalid(),
 * which should be implemented, returns true, this exception is thrown.
 */
public final class AuthorizationFailedException extends ResponseBodyException {

    public AuthorizationFailedException(String requestId, String message, int errorCode) {
        super(requestId, message, errorCode);
    }
}
