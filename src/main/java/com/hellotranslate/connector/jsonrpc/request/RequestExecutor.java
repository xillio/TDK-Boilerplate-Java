package com.hellotranslate.connector.jsonrpc.request;

import com.hellotranslate.connector.jsonrpc.response.ResponseBody;
import com.hellotranslate.connector.jsonrpc.response.ResponseDtoFactory;
import com.hellotranslate.connector.service.ContentService;
import com.hellotranslate.connector.service.MetadataService;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.hellotranslate.connector.jsonrpc.Method.*;
import static com.hellotranslate.connector.jsonrpc.request.scope.SupportedProjectScopes.PATH_CHILDREN_ENTITY;
import static com.hellotranslate.connector.jsonrpc.request.scope.SupportedProjectScopes.PATH_CHILDREN_REFERENCE;
import static com.hellotranslate.connector.jsonrpc.response.LocHubErrorCodes.NO_SUCH_SCOPE;

@Component
public class RequestExecutor {

    private final ResponseDtoFactory responseFactory;
    private final MetadataService metadataService;
    private final ContentService contentService;

    public RequestExecutor(
            ResponseDtoFactory responseFactory,
            MetadataService metadataService,
            ContentService contentService)
    {
        this.responseFactory = responseFactory;
        this.metadataService = metadataService;
        this.contentService = contentService;
    }

    public ResponseBody execute(RequestDto requestDto)
    {
        return switch (requestDto.method()) {
            case ENTITY_GET -> executeEntityGetRequest(requestDto);
            case ENTITY_GET_BINARY -> executeGetBinaryContentRequest(requestDto);
            case ENTITY_CREATE -> executeUploadTranslationRequest(requestDto);

            default -> responseFactory.createInvalidConfigurationResponse(requestDto.id());
        };
    }

    private ResponseBody executeEntityGetRequest(RequestDto requestDto)
    {
        var scope = requestDto.params().getRequestParameters().getProjectionScopes()[0];
        return switch (scope) {
            case PATH_CHILDREN_ENTITY -> metadataService.getChildren(
                    requestDto.id(),
                    requestDto.params().getConfig(),
                    requestDto.params().getXdip());

            case PATH_CHILDREN_REFERENCE -> metadataService.getReferences(
                    requestDto.id(),
                    requestDto.params().getConfig(),
                    requestDto.params().getXdip());

            default -> responseFactory.createErrorResponse(
                    requestDto.id(),
                    NO_SUCH_SCOPE,
                    new Exception("No such scope"), //todo find a way to throw a specific exception
                    Optional.empty());
        };
    }

    private ResponseBody executeGetBinaryContentRequest(RequestDto requestDto)
    {
        return contentService.getContent(
                requestDto.id(),
                requestDto.params()
                          .getConfig(),
                requestDto.params()
                          .getXdip());
    }

    private ResponseBody executeUploadTranslationRequest(RequestDto requestDto)
    {
        return contentService.upload(
                requestDto.id(),
                requestDto.params().getXdip(),
                requestDto.params().getConfig(),
                requestDto.params().getEntity(),
                requestDto.params()
                          .getBinaryContents());
    }
}
