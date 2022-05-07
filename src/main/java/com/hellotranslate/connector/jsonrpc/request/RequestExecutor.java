package com.hellotranslate.connector.jsonrpc.request;

import com.hellotranslate.connector.exception.MethodNotImplementedException;
import com.hellotranslate.connector.jsonrpc.response.ResponseDto;
import com.hellotranslate.connector.jsonrpc.response.ResponseDtoFactory;
import com.hellotranslate.connector.service.ContentService;
import com.hellotranslate.connector.service.MetadataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.hellotranslate.connector.jsonrpc.Method.*;
import static com.hellotranslate.connector.jsonrpc.response.LocHubErrorCodes.NO_SUCH_ENTITY;

@Component
@RequiredArgsConstructor
public class RequestExecutor {

    private final ResponseDtoFactory responseFactory;

    private final MetadataService metadataService;
    private final ContentService contentService;

    public ResponseDto execute(RequestDto requestDto)
    {
        return switch (requestDto.method()) {

            case ENTITY_GET -> {
                try {
                    yield metadataService.parseProjectionScopes(requestDto)
                                         .getReferences(
                                                 requestDto.id(),
                                                 requestDto.params().getConfig(),
                                                 requestDto.params().getXdip());
                } catch (MethodNotImplementedException e) {
                    yield responseFactory.createErrorResponse(
                            requestDto.id(),
                            NO_SUCH_ENTITY,
                            requestDto.params().getConfig(),
                            e.getMessage(),
                            Optional.empty());
                }
            }

            case ENTITY_GET_BINARY -> contentService.getContent(
                    requestDto.id(),
                    requestDto.params()
                              .getConfig(),
                    requestDto.params()
                              .getXdip());

            case ENTITY_CREATE -> contentService.upload(
                    requestDto.id(),
                    requestDto.params().getXdip(),
                    requestDto.params().getConfig(),
                    requestDto.params().getEntity(),
                    requestDto.params()
                              .getBinaryContents());

            default -> responseFactory.createInvalidConfigurationResponse(
                    requestDto.id(),
                    requestDto.params()
                              .getConfig());
        };
    }
}
