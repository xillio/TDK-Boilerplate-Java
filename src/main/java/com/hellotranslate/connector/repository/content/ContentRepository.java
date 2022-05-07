package com.hellotranslate.connector.repository.content;

import com.hellotranslate.connector.jsonrpc.request.dtos.ConfigDto;
import com.hellotranslate.connector.exception.MethodNotImplementedException;
import com.hellotranslate.connector.jsonrpc.request.dtos.EntityDto;
import com.hellotranslate.connector.model.XDIP;

import java.io.InputStream;

public interface ContentRepository {

    InputStream downloadContent(XDIP xdip)
            throws MethodNotImplementedException;

    InputStream uploadContent(
            XDIP xdip,
            ConfigDto config,
            EntityDto entity,
            String binaryContents)
            throws MethodNotImplementedException;
}
