package com.hellotranslate.connector.jsonrpc.request.scope;

import com.hellotranslate.connector.exception.MethodNotImplementedException;
import com.hellotranslate.connector.jsonrpc.request.dtos.ConfigDto;
import com.hellotranslate.connector.jsonrpc.response.ResponseDto;

public class PathChildrenReference implements ProjectionScope {

    @Override
    public ResponseDto getReferences(
            ConfigDto config,
            String xdip)
            throws MethodNotImplementedException
    {
        throw new MethodNotImplementedException();
    }
}
