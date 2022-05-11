package com.hellotranslate.connector.exception.jsonrpc.response;

/**
 * InvalidConfigurationException is by end-user implementing validation of the 'config' object in 'params'
 */
//todo extend documentation
public final class InvalidConfigurationException extends ResponseBodyException {

    public InvalidConfigurationException(String requestId, String message, int errorCode) {
        super(requestId, message, errorCode);
    }
}
