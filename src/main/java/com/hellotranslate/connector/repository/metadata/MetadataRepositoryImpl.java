package com.hellotranslate.connector.repository.metadata;

import com.hellotranslate.connector.exception.jsonrpc.response.MethodNotImplementedException;
import com.hellotranslate.connector.jsonrpc.EntityDto;
import com.hellotranslate.connector.jsonrpc.request.XDIP;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

import static com.hellotranslate.connector.exception.lochub.LocHubErrors.CONNECTOR_OPERATION_FAILED;

/**
 * This class is an implementation of the {@link MetadataRepository} interface.
 * <p>
 * To make TDK work, you must write an implementation how to get child entities and entity's metadata from your datastore.
 */
@Repository
public final class MetadataRepositoryImpl implements MetadataRepository {

    @Override
    public List<EntityDto> listChildren(String requestId, XDIP xdip, Map<String, Object> config) {
        // TODO: implement
        throw new MethodNotImplementedException(requestId, CONNECTOR_OPERATION_FAILED.code());
    }

    @Override
    public List<String> listReferences(String requestId, XDIP xdip, Map<String, Object> config) {
        // TODO: implement
        throw new MethodNotImplementedException(requestId, CONNECTOR_OPERATION_FAILED.code());
    }

    @Override
    public EntityDto getEntityByXdip(String requestId, XDIP xdip, Map<String, Object> config) {
        // TODO: implement
        throw new MethodNotImplementedException(requestId, CONNECTOR_OPERATION_FAILED.code());
    }
}
