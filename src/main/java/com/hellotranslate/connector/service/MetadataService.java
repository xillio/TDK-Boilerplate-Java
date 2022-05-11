package com.hellotranslate.connector.service;

import com.hellotranslate.connector.jsonrpc.response.ResponseBody;
import com.hellotranslate.connector.jsonrpc.response.ResponseDtoFactory;
import com.hellotranslate.connector.model.XDIP;
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
        var children = metadataRepository.listChildren(requestId, xdip, config);
        return responseFactory.createSuccessResponse(requestId, children);
    }

    public ResponseBody getReferences(String requestId, Map<String, Object> config, XDIP xdip) {
        var references = metadataRepository.listReferences(requestId, xdip, config);
        return responseFactory.createSuccessResponse(requestId, references);
    }
}
