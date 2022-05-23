package com.hellotranslate.connector.service;

import com.hellotranslate.connector.exception.jsonrpc.response.ConnectorOperationFailedException;
import com.hellotranslate.connector.exception.jsonrpc.response.NoSuchEntityException;
import com.hellotranslate.connector.jsonrpc.response.dtos.EntityResult;
import com.hellotranslate.connector.model.EntityReference;
import com.hellotranslate.connector.model.XDIP;
import com.hellotranslate.connector.jsonrpc.response.ResponseBody;
import com.hellotranslate.connector.jsonrpc.response.ResponseDtoFactory;
import com.hellotranslate.connector.repository.metadata.MetadataRepository;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

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
        return responseFactory.createSuccessResponse(
                requestId,
                new EntityResult(null, children, null)
        );
    }

    public ResponseBody getReferences(String requestId, Map<String, Object> config, XDIP xdip) {
        var xdips = metadataRepository.listReferences(xdip, config);
        if (xdips == null) {
            throw new ConnectorOperationFailedException("Could not obtain references");
        }

        var references = xdips.stream()
                .map(EntityReference::new)
                .collect(Collectors.toList());

        return responseFactory.createSuccessResponse(
                requestId,
                new EntityResult(null, null, references)
        );
    }

    public ResponseBody getEntity(String requestId, Map<String, Object> config, XDIP xdip) {
        var entity = metadataRepository.getEntityByXdip(xdip, config);
        if (entity == null) {
            throw new NoSuchEntityException(String.format("Entity %s not found", xdip.toString()));
        }
        return responseFactory.createSuccessResponse(
                requestId,
                new EntityResult(entity, null, null)
        );
    }
}
