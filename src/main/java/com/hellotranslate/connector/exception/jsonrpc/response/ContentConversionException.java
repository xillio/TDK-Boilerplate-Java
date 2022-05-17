package com.hellotranslate.connector.exception.jsonrpc.response;

import static com.hellotranslate.connector.exception.lochub.LocHubErrors.CONNECTOR_OPERATION_FAILED;

public final class ContentConversionException extends ResponseBodyException {

    public ContentConversionException(String message) {
        super(message, CONNECTOR_OPERATION_FAILED.code());
    }
}
