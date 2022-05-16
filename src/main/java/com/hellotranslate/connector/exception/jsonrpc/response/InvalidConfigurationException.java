package com.hellotranslate.connector.exception.jsonrpc.response;

/**
 * InvalidConfigurationException is thrown when any parameter except credentials in 'config' is invalid.
 * <p>
 * User should implement the config validation for values they send.
 */
public final class InvalidConfigurationException extends ResponseBodyException {

    public InvalidConfigurationException(String message, int errorCode) {
        super(message, errorCode);
    }
}
