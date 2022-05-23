package com.hellotranslate.connector.service;

import com.hellotranslate.connector.exception.jsonrpc.response.ConnectorOperationFailedException;
import com.hellotranslate.connector.model.XDIP;
import com.hellotranslate.connector.jsonrpc.response.ResponseBody;
import com.hellotranslate.connector.jsonrpc.response.ResponseDtoFactory;
import com.hellotranslate.connector.model.Entity;
import com.hellotranslate.connector.repository.content.ContentRepository;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ContentService {

    private final ResponseDtoFactory responseFactory;
    private final ContentRepository contentRepository;
    private final ContentConversionService conversionService;

    public ContentService(ResponseDtoFactory responseFactory, ContentRepository contentRepository, ContentConversionService conversionService) {
        this.responseFactory = responseFactory;
        this.contentRepository = contentRepository;
        this.conversionService = conversionService;
    }

    public ResponseBody getContent(String requestId, Map<String, Object> config, XDIP xdip) {
        var binaryContent = contentRepository.downloadContent(xdip, config);
        String base64String = conversionService.inputStreamToBase64String(binaryContent);
        return responseFactory.createSuccessResponse(requestId, base64String);
    }

    public ResponseBody upload(String requestId, XDIP xdip, Map<String, Object> config, Entity entity, String binaryContents) {
        var inputStream = conversionService.stringToInputStream(binaryContents);
        var entityDto = contentRepository.uploadContent(xdip, config, entity, inputStream);
        if (entityDto == null) {
            throw new ConnectorOperationFailedException("Failed to upload content");
        }
        return responseFactory.createSuccessResponse(requestId, entityDto);
    }
}
