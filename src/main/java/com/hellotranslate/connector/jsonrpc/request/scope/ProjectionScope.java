package com.hellotranslate.connector.jsonrpc.request.scope;

import com.hellotranslate.connector.exception.MethodNotImplementedException;
import com.hellotranslate.connector.jsonrpc.response.ResponseDto;
import com.hellotranslate.connector.model.XDIP;

import java.util.Map;

public interface ProjectionScope {

    ResponseDto getReferences(
            String id,
            Map<String, Object> config,
            XDIP xdip)
            throws MethodNotImplementedException;
}
