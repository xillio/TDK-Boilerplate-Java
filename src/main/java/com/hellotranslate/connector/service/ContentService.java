package com.hellotranslate.connector.service;

import com.hellotranslate.connector.exception.jsonrpc.response.ContentConversionException;
import com.hellotranslate.connector.jsonrpc.EntityDto;
import com.hellotranslate.connector.jsonrpc.response.ResponseBody;
import com.hellotranslate.connector.jsonrpc.response.ResponseDtoFactory;
import com.hellotranslate.connector.model.XDIP;
import com.hellotranslate.connector.repository.content.ContentRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

import static com.hellotranslate.connector.exception.lochub.LocHubErrors.CONNECTOR_OPERATION_FAILED;

@Service
public class ContentService {

    private final ResponseDtoFactory responseFactory;
    private final ContentRepository contentRepository;
    private final ContentConverterService converterService;

    public ContentService(ResponseDtoFactory responseFactory, ContentRepository contentRepository, ContentConverterService converterService) {
        this.responseFactory = responseFactory;
        this.contentRepository = contentRepository;
        this.converterService = converterService;
    }

    public ResponseBody getContent(String requestId, Map<String, Object> config, XDIP xdip) {
        try {
            var binaryContent = contentRepository.downloadContent(requestId, xdip, config);
            String base64String = converterService.inputStreamToBase64String(requestId, binaryContent);
            return responseFactory.createSuccessResponse(requestId, base64String);
        } catch (IOException e) {
            throw new ContentConversionException(requestId, "Failed to convert content to base64 format", CONNECTOR_OPERATION_FAILED.code());
        }
    }

    public ResponseBody upload(String requestId, XDIP xdip, Map<String, Object> config, EntityDto entity, String binaryContents) {
        var inputStream = converterService.stringToInputStream(binaryContents);
        var entityDto = contentRepository.uploadContent(requestId, xdip, config, entity, inputStream);
        return responseFactory.createSuccessResponse(requestId, entityDto);
    }
}
