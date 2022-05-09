package com.hellotranslate.connector.jsonrpc.request.scope;

import com.hellotranslate.connector.jsonrpc.response.LocHubErrorCodes;
import com.hellotranslate.connector.jsonrpc.response.ResponseDto;
import com.hellotranslate.connector.jsonrpc.response.ResponseDtoFactory;
import com.hellotranslate.connector.model.XDIP;

import java.util.Map;
import java.util.Optional;

public record UnsupportedScope(ResponseDtoFactory responseFactory) implements ProjectionScope {

    @Override
    public ResponseDto getReferences(
            String id,
            Map<String, Object> config,
            XDIP xdip)
    {
        return responseFactory.createErrorResponse(
                id,
                LocHubErrorCodes.NO_SUCH_SCOPE,
                config,
                new Exception(),
                Optional.of("Scope is empty or does not exist"));
    }
}