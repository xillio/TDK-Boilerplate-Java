package com.hellotranslate.connector.repository.metadata;

import com.hellotranslate.connector.exception.jsonrpc.response.MethodNotImplementedException;
import com.hellotranslate.connector.jsonrpc.EntityDto;
import com.hellotranslate.connector.model.XDIP;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

import static com.hellotranslate.connector.exception.lochub.LocHubErrors.CONNECTOR_OPERATION_FAILED;

@Repository
public final class MetadataRepositoryImpl implements MetadataRepository {

    @Override
    public List<EntityDto> listChildren(String requestId, XDIP xdip, Map<String, Object> config) {
        throw new MethodNotImplementedException(requestId, CONNECTOR_OPERATION_FAILED.code());
    }

    @Override
    public List<XDIP> listReferences(String requestId, XDIP xdip, Map<String, Object> config) {
        throw new MethodNotImplementedException(requestId, CONNECTOR_OPERATION_FAILED.code());
    }
}
