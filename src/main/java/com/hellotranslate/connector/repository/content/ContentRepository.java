package com.hellotranslate.connector.repository.content;

import com.hellotranslate.connector.jsonrpc.request.dtos.ConfigDto;
import com.hellotranslate.connector.exception.MethodNotImplementedException;
import com.hellotranslate.connector.jsonrpc.request.dtos.EntityDto;

import java.io.InputStream;

public interface ContentRepository {

    InputStream downloadContent(String xdip)
            throws MethodNotImplementedException;

    InputStream uploadContent(
            String xdip,
            ConfigDto config,
            EntityDto entity,
            String binaryContents)
            throws MethodNotImplementedException;
}
