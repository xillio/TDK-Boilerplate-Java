package com.hellotranslate.connector.repository.metadata;

import com.hellotranslate.connector.exception.jsonrpc.response.MethodNotImplementedException;
import com.hellotranslate.connector.jsonrpc.EntityDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

import static com.hellotranslate.connector.exception.lochub.LocHubErrors.CONNECTOR_OPERATION_FAILED;

@Repository
public final class MetadataRepositoryImpl implements MetadataRepository {

    @Override
    public List<EntityDto> listChildren(String requestId, String xdip, Map<String, Object> config) {
        throw new MethodNotImplementedException(requestId, CONNECTOR_OPERATION_FAILED.code());
    }

    @Override
    public List<String> listReferences(String requestId, String xdip, Map<String, Object> config) {
        throw new MethodNotImplementedException(requestId, CONNECTOR_OPERATION_FAILED.code());
    }
}
