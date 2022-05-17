package com.hellotranslate.connector.exception.jsonrpc.response;

import static com.hellotranslate.connector.exception.lochub.LocHubErrors.INVALID_CONFIGURATION;

/**
 * InvalidConfigurationException is thrown when any parameter except credentials in 'config' is invalid.
 * <p>
 * User should implement the config validation for values they send.
 */
public final class InvalidConfigurationException extends ResponseBodyException {

    public InvalidConfigurationException(String message) {
        super(message, INVALID_CONFIGURATION.code());
    }
}
