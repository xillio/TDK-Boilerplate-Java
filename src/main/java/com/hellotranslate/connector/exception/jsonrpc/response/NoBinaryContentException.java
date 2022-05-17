package com.hellotranslate.connector.exception.jsonrpc.response;

import static com.hellotranslate.connector.exception.lochub.LocHubErrors.NO_BINARY_CONTENT;

public final class NoBinaryContentException extends ResponseBodyException {

    public NoBinaryContentException(String message) {
        super(message, NO_BINARY_CONTENT.code());
    }
}
