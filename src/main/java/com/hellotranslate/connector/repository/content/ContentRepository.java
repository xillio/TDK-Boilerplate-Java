package com.hellotranslate.connector.repository.content;

import com.hellotranslate.connector.exception.MethodNotImplementedException;
import com.hellotranslate.connector.jsonrpc.EntityDto;
import com.hellotranslate.connector.model.XDIP;

import java.io.InputStream;
import java.util.Map;

public sealed interface ContentRepository permits ContentRepositoryImpl {

    InputStream downloadContent(XDIP xdip, Map<String, Object> config) throws MethodNotImplementedException;

    EntityDto uploadContent(XDIP xdip, Map<String, Object> config, EntityDto entity, InputStream binaryContents) throws MethodNotImplementedException;
}
