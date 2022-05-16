package com.hellotranslate.connector.exception.jsonrpc.response;

public final class ConnectorOperationFailedException extends ResponseBodyException {

    public ConnectorOperationFailedException(String message, int errorCode) {
        super(message, errorCode);
    }
}
