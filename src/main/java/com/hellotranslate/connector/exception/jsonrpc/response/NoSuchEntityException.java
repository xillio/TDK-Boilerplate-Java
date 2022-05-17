package com.hellotranslate.connector.exception.jsonrpc.response;

import static com.hellotranslate.connector.exception.lochub.LocHubErrors.NO_SUCH_ENTITY;

public final class NoSuchEntityException extends ResponseBodyException {

    public NoSuchEntityException(String message) {
        super(message, NO_SUCH_ENTITY.code());
    }
}
