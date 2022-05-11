package com.hellotranslate.connector.service;

import com.hellotranslate.connector.exception.response.ContentDownloadingFailedException;
import com.hellotranslate.connector.jsonrpc.EntityDto;
import com.hellotranslate.connector.jsonrpc.response.ResponseBody;
import com.hellotranslate.connector.jsonrpc.response.ResponseDtoFactory;
import com.hellotranslate.connector.model.XDIP;
import com.hellotranslate.connector.repository.content.ContentRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import static com.hellotranslate.connector.exception.response.LocHubErrorCodes.CONNECTOR_OPERATION_FAILED;

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

    public ResponseBody getContent(String id, Map<String, Object> config, XDIP xdip) {
        try {
            var binaryContent = contentRepository.downloadContent(xdip, config);
            var base64String = converterService.inputStreamToBase64String(binaryContent);
            return responseFactory.createSuccessResponse(id, base64String);
        } catch (ContentDownloadingFailedException e) {
            return responseFactory.createErrorResponse(id, e.getErrorCode(), e, Optional.empty());
        } catch (IOException e) {
            return responseFactory.createErrorResponse(id, CONNECTOR_OPERATION_FAILED, e, Optional.empty());
        }
    }

    public ResponseBody upload(String id, XDIP xdip, Map<String, Object> config, EntityDto entity, String binaryContents) {
        try {
            var inputStream = converterService.stringToInputStream(binaryContents);
            var entityDto = contentRepository.uploadContent(xdip, config, entity, inputStream);
            return responseFactory.createSuccessResponse(id, entityDto);
        } catch (Exception e) {
            return responseFactory.createErrorResponse(
                    id, CONNECTOR_OPERATION_FAILED, e, Optional.of("Failed to upload translation")
            );//todo catch several specific exceptions
        }
    }
}
