package com.hellotranslate.connector.exception.jsonrpc.response;

public final class ConnectorOperationFailedException extends ResponseBodyException {

    public ConnectorOperationFailedException(String requestId, String message, int errorCode) {
        super(requestId, message, errorCode);
    }
}
