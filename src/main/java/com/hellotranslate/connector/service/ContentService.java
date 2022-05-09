package com.hellotranslate.connector.service;

import com.hellotranslate.connector.jsonrpc.request.dtos.EntityDto;
import com.hellotranslate.connector.jsonrpc.response.LocHubErrorCodes;
import com.hellotranslate.connector.jsonrpc.response.ResponseDto;
import com.hellotranslate.connector.jsonrpc.response.ResponseDtoFactory;
import com.hellotranslate.connector.model.XDIP;
import com.hellotranslate.connector.repository.content.ContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContentService {

    private final ResponseDtoFactory responseFactory;
    private final ContentRepository contentRepository;

    public ResponseDto getContent(
            String id,
            Map<String, Object> config,
            XDIP xdip)
    {
        try {
            var binaryContent = contentRepository.downloadContent(xdip);
            return responseFactory.createSuccessResponse(id, config, binaryContent.readAllBytes());
        } catch (Exception e) {
            return responseFactory.createErrorResponse(
                    id,
                    LocHubErrorCodes.NO_BINARY_CONTENT,
                    config,
                    e,
                    Optional.empty());
        }
    }

    public ResponseDto upload(
            String id,
            XDIP xdip,
            Map<String, Object> config,
            EntityDto entity,
            String binaryContents)
    {
        try {
            var inputStream = contentRepository.uploadContent(xdip, config, entity, binaryContents);
            return responseFactory.createSuccessResponse(id, config, inputStream.readAllBytes());
        } catch (Exception e) {
            return responseFactory.createErrorResponse(
                    id,
                    LocHubErrorCodes.CONNECTOR_OPERATION_FAILED,
                    config,
                    e,
                    Optional.of("Failed to upload translation")
            );
        }
    }
}
