package com.hellotranslate.connector.service;

import com.hellotranslate.connector.jsonrpc.response.ResponseBody;
import com.hellotranslate.connector.jsonrpc.response.ResponseDtoFactory;
import com.hellotranslate.connector.model.XDIP;
import com.hellotranslate.connector.repository.metadata.MetadataRepository;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

import static com.hellotranslate.connector.exception.response.LocHubErrorCodes.CONNECTOR_OPERATION_FAILED;

@Service
public class MetadataService {

    private final ResponseDtoFactory responseFactory;
    private final MetadataRepository metadataRepository;

    public MetadataService(ResponseDtoFactory responseFactory, MetadataRepository metadataRepository) {
        this.responseFactory = responseFactory;
        this.metadataRepository = metadataRepository;
    }

    public ResponseBody getChildren(String id, Map<String, Object> config, XDIP xdip) {
        try {
            var children = metadataRepository.listChildren(xdip, config);
            return responseFactory.createSuccessResponse(id, children);
        } catch (Exception e) { //todo: catch specific exception rewrite with handler
            return responseFactory.createErrorResponse(
                    id, CONNECTOR_OPERATION_FAILED, e, Optional.of("Failed to get children")
            );
        }
    }

    public ResponseBody getReferences(String id, Map<String, Object> config, XDIP xdip) {
        try {
            var references = metadataRepository.listReferences(xdip, config);
            return responseFactory.createSuccessResponse(id, references);
        } catch (Exception e) { //todo: catch specific exception
            return responseFactory.createErrorResponse(
                    id, CONNECTOR_OPERATION_FAILED, e, Optional.of("Failed to get references")
            );
        }
    }
}
