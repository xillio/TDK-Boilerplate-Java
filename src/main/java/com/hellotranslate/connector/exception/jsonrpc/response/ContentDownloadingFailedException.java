package com.hellotranslate.connector.exception.jsonrpc.response;

import static com.hellotranslate.connector.exception.lochub.LocHubErrors.CONNECTOR_OPERATION_FAILED;

public final class ContentDownloadingFailedException extends ResponseBodyException {

    public ContentDownloadingFailedException(String message) {
        super(message, CONNECTOR_OPERATION_FAILED.code());
    }
}
