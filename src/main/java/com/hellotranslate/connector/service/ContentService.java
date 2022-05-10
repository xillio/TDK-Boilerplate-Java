package com.hellotranslate.connector.service;

import com.hellotranslate.connector.jsonrpc.request.dtos.EntityDto;
import com.hellotranslate.connector.jsonrpc.response.ResponseBody;
import com.hellotranslate.connector.jsonrpc.response.ResponseDtoFactory;
import com.hellotranslate.connector.model.XDIP;
import com.hellotranslate.connector.repository.content.ContentRepository;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

import static com.hellotranslate.connector.jsonrpc.response.LocHubErrorCodes.CONNECTOR_OPERATION_FAILED;

@Service
public class ContentService {

    private final ResponseDtoFactory responseFactory;
    private final ContentRepository contentRepository;
    private final ContentEncodeService encodeService;

    public ContentService(
            ResponseDtoFactory responseFactory,
            ContentRepository contentRepository,
            ContentEncodeService encodeService)
    {
        this.responseFactory = responseFactory;
        this.contentRepository = contentRepository;
        this.encodeService = encodeService;
    }

    public ResponseBody getContent(
            String id,
            Map<String, Object> config,
            XDIP xdip)
    {
        try {
            var binaryContent = contentRepository.downloadContent(xdip, config);
            var result = encodeService.convertToBase64String(binaryContent);
            return responseFactory.createSuccessResponse(id, result);
        } catch (Exception e) {//todo catch several specific exceptions
            return responseFactory.createErrorResponse(
                    id,
                    CONNECTOR_OPERATION_FAILED,
                    e,
                    Optional.empty());
        }
    }

    public ResponseBody upload(
            String id,
            XDIP xdip,
            Map<String, Object> config,
            EntityDto entity,
            String binaryContents)
    {
        try {
            var entityDto = contentRepository.uploadContent(xdip, config, entity, binaryContents);
            return responseFactory.createSuccessResponse(id, entityDto);
        } catch (Exception e) {
            return responseFactory.createErrorResponse( //todo catch several specific exceptions
                                                        id,
                                                        CONNECTOR_OPERATION_FAILED,
                                                        e,
                                                        Optional.of("Failed to upload translation")
            );
        }
    }
}
