package com.hellotranslate.connector.exception.jsonrpc.response;

import static com.hellotranslate.connector.exception.lochub.LocHubErrors.OPERATION_NOT_ALLOWED;

public final class OperationNotAllowedException extends ResponseBodyException {

    public OperationNotAllowedException(String message) {
        super(message, OPERATION_NOT_ALLOWED.code());
    }
}
