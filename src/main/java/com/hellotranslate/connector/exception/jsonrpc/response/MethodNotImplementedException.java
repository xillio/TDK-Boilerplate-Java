package com.hellotranslate.connector.exception.jsonrpc.response;

import static com.hellotranslate.connector.exception.lochub.LocHubErrors.CONNECTOR_OPERATION_FAILED;

public final class MethodNotImplementedException extends ResponseBodyException {

    private static final String DEFAULT_MESSAGE = "Method not implemented";

    public MethodNotImplementedException() {
        super(DEFAULT_MESSAGE, CONNECTOR_OPERATION_FAILED.code());
    }
}
