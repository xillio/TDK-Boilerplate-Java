package com.hellotranslate.connector.exception.jsonrpc.response;

import static com.hellotranslate.connector.exception.lochub.LocHubErrors.CONNECTOR_OPERATION_FAILED;

public final class ConnectorOperationFailedException extends ResponseBodyException {

    public ConnectorOperationFailedException(String message) {
        super(message, CONNECTOR_OPERATION_FAILED.code());
    }
}
