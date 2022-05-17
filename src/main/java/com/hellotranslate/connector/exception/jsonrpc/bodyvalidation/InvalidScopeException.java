package com.hellotranslate.connector.exception.jsonrpc.bodyvalidation;

import static com.hellotranslate.connector.exception.lochub.LocHubErrors.NO_SUCH_SCOPE;

public final class InvalidScopeException extends RequestBodyValidationException {

    public InvalidScopeException(String message) {
        super(message, NO_SUCH_SCOPE.code());
    }
}
