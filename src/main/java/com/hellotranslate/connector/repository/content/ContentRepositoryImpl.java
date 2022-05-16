package com.hellotranslate.connector.repository.content;

import com.hellotranslate.connector.exception.jsonrpc.response.MethodNotImplementedException;
import com.hellotranslate.connector.jsonrpc.response.dtos.EntityDto;
import com.hellotranslate.connector.jsonrpc.request.XDIP;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.util.Map;

import static com.hellotranslate.connector.exception.lochub.LocHubErrors.CONNECTOR_OPERATION_FAILED;

/**
 * This class is an implementation of the {@link ContentRepository} interface.
 * <p>
 * To make TDK work, you must write an implementation how to retrieve and
 * upload the content to the place where it is stored.
 */
@Repository
public final class ContentRepositoryImpl implements ContentRepository {

    @Override
    public InputStream downloadContent(XDIP xdip, Map<String, Object> config) {
        // TODO: implement
        throw new MethodNotImplementedException(CONNECTOR_OPERATION_FAILED.code());
    }

    @Override
    public EntityDto uploadContent(XDIP xdip,
                                   Map<String, Object> config,
                                   EntityDto entity,
                                   InputStream binaryContents) {
        // TODO: implement
        throw new MethodNotImplementedException(CONNECTOR_OPERATION_FAILED.code());
    }
}
