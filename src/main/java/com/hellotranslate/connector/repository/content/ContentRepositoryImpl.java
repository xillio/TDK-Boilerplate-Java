package com.hellotranslate.connector.repository.content;

import com.hellotranslate.connector.exception.MethodNotImplementedException;
import com.hellotranslate.connector.jsonrpc.request.dtos.ConfigDto;
import com.hellotranslate.connector.jsonrpc.request.dtos.EntityDto;
import org.springframework.stereotype.Repository;

import java.io.InputStream;

@Repository
public class ContentRepositoryImpl implements ContentRepository {

    @Override
    public InputStream downloadContent(String xdip)
            throws MethodNotImplementedException
    {
        throw new MethodNotImplementedException();
    }

    @Override
    public InputStream uploadContent(
            String xdip,
            ConfigDto config,
            EntityDto entity,
            String binaryContents)
            throws MethodNotImplementedException
    {
        throw new MethodNotImplementedException();
    }
}
