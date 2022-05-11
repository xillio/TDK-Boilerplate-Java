package com.hellotranslate.connector.repository.content;

import com.hellotranslate.connector.exception.MethodNotImplementedException;
import com.hellotranslate.connector.jsonrpc.EntityDto;
import com.hellotranslate.connector.model.XDIP;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.util.Map;

/**
 * This class is an implementation of the {@link ContentRepository} interface.
 * <p>
 * To make TDK work, you must write an implementation how to retrieve and
 * upload the content to the place where it is stored.
 */
@Repository
public final class ContentRepositoryImpl implements ContentRepository {

    @Override
    public InputStream downloadContent(XDIP xdip, Map<String, Object> config) throws MethodNotImplementedException {
        throw new MethodNotImplementedException();
    }

    @Override
    public EntityDto uploadContent(
            XDIP xdip,
            Map<String, Object> config,
            EntityDto entity,
            InputStream binaryContents) throws MethodNotImplementedException {
        throw new MethodNotImplementedException();
    }
}
