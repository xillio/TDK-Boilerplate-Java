package com.hellotranslate.connector.service;

import com.hellotranslate.connector.exception.jsonrpc.response.ConnectorOperationFailedException;
import com.hellotranslate.connector.jsonrpc.request.XDIP;
import com.hellotranslate.connector.jsonrpc.response.ResponseBody;
import com.hellotranslate.connector.jsonrpc.response.ResponseDtoFactory;
import com.hellotranslate.connector.repository.metadata.MetadataRepository;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.hellotranslate.connector.exception.lochub.LocHubErrors.CONNECTOR_OPERATION_FAILED;

@Service
public class MetadataService {

    private final ResponseDtoFactory responseFactory;
    private final MetadataRepository metadataRepository;

    public MetadataService(ResponseDtoFactory responseFactory, MetadataRepository metadataRepository) {
        this.responseFactory = responseFactory;
        this.metadataRepository = metadataRepository;
    }

    public ResponseBody getChildren(String requestId, Map<String, Object> config, XDIP xdip) {
        var children = metadataRepository.listChildren(requestId, xdip, config);
        if (children == null) {
            throw new ConnectorOperationFailedException(requestId, "No children found", CONNECTOR_OPERATION_FAILED.code());
        }
        return responseFactory.createSuccessResponse(requestId, children);
    }

    public ResponseBody getReferences(String requestId, Map<String, Object> config, XDIP xdip) {
        var references = metadataRepository.listReferences(requestId, xdip, config);
        if (references == null) {
            throw new ConnectorOperationFailedException(requestId, "Could not obtain references", CONNECTOR_OPERATION_FAILED.code());
        }
        return responseFactory.createSuccessResponse(requestId, references);
    }

    public ResponseBody getEntity(String requestId, Map<String, Object> config, XDIP xdip) {
        var entity = metadataRepository.getEntityByXdip(requestId, xdip, config);
        if (entity == null) {
            throw new ConnectorOperationFailedException(requestId, "Entity not found", CONNECTOR_OPERATION_FAILED.code());
        }
        return responseFactory.createSuccessResponse(requestId, entity);
    }
}
