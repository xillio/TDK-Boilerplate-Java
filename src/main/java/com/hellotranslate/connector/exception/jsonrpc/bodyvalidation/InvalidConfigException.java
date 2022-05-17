package com.hellotranslate.connector.exception.jsonrpc.bodyvalidation;

import static com.hellotranslate.connector.exception.lochub.LocHubErrors.INVALID_CONFIGURATION;

public final class InvalidConfigException extends RequestBodyValidationException {

    public InvalidConfigException(String message) {
        super(message, INVALID_CONFIGURATION.code());
    }
}
