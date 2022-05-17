package com.hellotranslate.connector.repository.metadata;

import com.hellotranslate.connector.exception.jsonrpc.response.MethodNotImplementedException;
import com.hellotranslate.connector.jsonrpc.request.XDIP;
import com.hellotranslate.connector.jsonrpc.response.dtos.EntityDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * This class is an implementation of the {@link MetadataRepository} interface.
 * <p>
 * To make TDK work, you must write an implementation how to get child entities and entity's metadata from your datastore.
 */
@Repository
public final class MetadataRepositoryImpl implements MetadataRepository {

    @Override
    public List<EntityDto> listChildren(XDIP xdip, Map<String, Object> config) {
        // TODO: implement
        throw new MethodNotImplementedException();
    }

    @Override
    public List<XDIP> listReferences(XDIP xdip, Map<String, Object> config) {
        // TODO: implement
        throw new MethodNotImplementedException();
    }

    @Override
    public EntityDto getEntityByXdip(XDIP xdip, Map<String, Object> config) {
        // TODO: implement
        throw new MethodNotImplementedException();
    }
}
