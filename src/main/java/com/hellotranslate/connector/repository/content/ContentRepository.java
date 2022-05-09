package com.hellotranslate.connector.repository.content;

import com.hellotranslate.connector.jsonrpc.request.dtos.ConfigDto;
import com.hellotranslate.connector.exception.MethodNotImplementedException;
import com.hellotranslate.connector.jsonrpc.request.dtos.EntityDto;
import com.hellotranslate.connector.model.XDIP;

import java.io.InputStream;
import java.util.Map;

public interface ContentRepository {

    InputStream downloadContent(XDIP xdip)
            throws MethodNotImplementedException;

    InputStream uploadContent(
            XDIP xdip,
            Map<String, Object> config,
            EntityDto entity,
            String binaryContents)
            throws MethodNotImplementedException;
}
