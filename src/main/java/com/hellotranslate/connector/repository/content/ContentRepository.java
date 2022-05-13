package com.hellotranslate.connector.repository.content;

import com.hellotranslate.connector.exception.jsonrpc.response.MethodNotImplementedException;
import com.hellotranslate.connector.jsonrpc.EntityDto;
import com.hellotranslate.connector.jsonrpc.request.XDIP;

import java.io.InputStream;
import java.util.Map;

public sealed interface ContentRepository permits ContentRepositoryImpl {

    InputStream downloadContent(String requestId, XDIP xdip, Map<String, Object> config) throws MethodNotImplementedException;

    EntityDto uploadContent(String requestId, XDIP xdip, Map<String, Object> config, EntityDto entity, InputStream binaryContents) throws MethodNotImplementedException;
}
