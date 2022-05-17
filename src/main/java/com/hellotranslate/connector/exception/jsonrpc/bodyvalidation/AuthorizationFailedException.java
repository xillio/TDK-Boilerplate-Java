package com.hellotranslate.connector.exception.jsonrpc.bodyvalidation;

import com.hellotranslate.connector.exception.jsonrpc.response.ResponseBodyException;
import com.hellotranslate.connector.service.ConfigValidationService;

import static com.hellotranslate.connector.exception.lochub.LocHubErrors.AUTHORIZATION_FAILED;

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

    public AuthorizationFailedException() {
        super(AUTHORIZATION_FAILED.message(), AUTHORIZATION_FAILED.code());
    }

    public AuthorizationFailedException(String message) {
        super(message, AUTHORIZATION_FAILED.code());
    }
}
