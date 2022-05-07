package com.hellotranslate.connector.jsonrpc.request.scope;

import com.hellotranslate.connector.exception.MethodNotImplementedException;
import com.hellotranslate.connector.jsonrpc.request.dtos.ConfigDto;
import com.hellotranslate.connector.jsonrpc.response.ResponseDto;
import com.hellotranslate.connector.model.XDIP;

public class PathChildrenEntity implements ProjectionScope {

    @Override
    public ResponseDto getReferences(
            String id,
            ConfigDto config,
            XDIP xdip)
            throws MethodNotImplementedException
    {
        throw new MethodNotImplementedException();
    }
}
