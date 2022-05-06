package com.hellotranslate.connector.service;

import com.hellotranslate.connector.jsonrpc.request.dtos.ConfigDto;
import com.hellotranslate.connector.jsonrpc.response.LocHubErrorCodes;
import com.hellotranslate.connector.jsonrpc.response.ResponseDtoFactory;
import com.hellotranslate.connector.repository.content.ContentRepository;
import lombok.RequiredArgsConstructor;
import com.hellotranslate.connector.jsonrpc.request.dtos.EntityDto;
import com.hellotranslate.connector.jsonrpc.response.ResponseDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContentService {

    private final ResponseDtoFactory responseFactory;
    private final ContentRepository contentRepository;

    public ResponseDto getContent(
            String id,
            ConfigDto config,
            String xdip)
    {
        try {
            var binaryContent = contentRepository.downloadContent(xdip);
            return responseFactory.createSuccessResponse(
                    id,
                    binaryContent.readAllBytes());
        } catch (Exception e) {
            return responseFactory.createErrorResponse(
                    id,
                    LocHubErrorCodes.NO_BINARY_CONTENT,
                    "Something went wrong during content download",
                    Optional.empty());
        }
    }

    public ResponseDto upload(
            String xdip,
            ConfigDto config,
            EntityDto entity,
            String binaryContents) //todo change to input stream
    {
        try {
            contentRepository.uploadContent(xdip, config, entity, binaryContents);
            return null;
        } catch (Exception e) {
            return responseFactory.createErrorResponse(
                    xdip,
                    LocHubErrorCodes.CONNECTOR_OPERATION_FAILED,
                    "Failed to upload translation",
                    Optional.of(e.getMessage())
            );
        }
    }
}
