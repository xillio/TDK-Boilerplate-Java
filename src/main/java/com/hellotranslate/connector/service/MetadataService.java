package com.hellotranslate.connector.service;

import com.hellotranslate.connector.exception.jsonrpc.response.ConnectorOperationFailedException;
import com.hellotranslate.connector.jsonrpc.request.XDIP;
import com.hellotranslate.connector.jsonrpc.response.ResponseBody;
import com.hellotranslate.connector.jsonrpc.response.ResponseDtoFactory;
import com.hellotranslate.connector.repository.metadata.MetadataRepository;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MetadataService {

    private final ResponseDtoFactory responseFactory;
    private final MetadataRepository metadataRepository;

    public MetadataService(ResponseDtoFactory responseFactory, MetadataRepository metadataRepository) {
        this.responseFactory = responseFactory;
        this.metadataRepository = metadataRepository;
    }

    public ResponseBody getChildren(String requestId, Map<String, Object> config, XDIP xdip) {
        var children = metadataRepository.listChildren(xdip, config);
        if (children == null) {
            throw new ConnectorOperationFailedException("No children found");
        }
        return responseFactory.createSuccessResponse(requestId, children);
    }

    public ResponseBody getReferences(String requestId, Map<String, Object> config, XDIP xdip) {
        var references = metadataRepository.listReferences(xdip, config);
        if (references == null) {
            throw new ConnectorOperationFailedException("Could not obtain references");
        }
        return responseFactory.createSuccessResponse(requestId, references);
    }

    public ResponseBody getEntity(String requestId, Map<String, Object> config, XDIP xdip) {
        var entity = metadataRepository.getEntityByXdip(xdip, config);
        if (entity == null) {
            throw new ConnectorOperationFailedException("Entity not found");
        }
        return responseFactory.createSuccessResponse(requestId, entity);
    }
}
