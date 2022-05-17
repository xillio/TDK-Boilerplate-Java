package com.hellotranslate.connector.exception.jsonrpc.bodyvalidation;

import static com.hellotranslate.connector.exception.lochub.LocHubErrors.NO_BINARY_CONTENT;

public final class NoContentToUploadException extends RequestBodyValidationException {

    public NoContentToUploadException(String message) {
        super(message, NO_BINARY_CONTENT.code());
    }
}
