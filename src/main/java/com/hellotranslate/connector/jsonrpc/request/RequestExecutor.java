package com.hellotranslate.connector.jsonrpc.request;

import com.hellotranslate.connector.exception.jsonrpc.bodyvalidation.InvalidMethodException;
import com.hellotranslate.connector.exception.jsonrpc.bodyvalidation.InvalidScopeException;
import com.hellotranslate.connector.jsonrpc.response.ResponseBody;
import com.hellotranslate.connector.service.ContentService;
import com.hellotranslate.connector.service.MetadataService;
import org.springframework.stereotype.Component;

import static com.hellotranslate.connector.exception.lochub.LocHubErrors.NO_SUCH_SCOPE;
import static com.hellotranslate.connector.jsonrpc.Method.*;
import static com.hellotranslate.connector.jsonrpc.request.scope.SupportedProjectScopes.PATH_CHILDREN_ENTITY;
import static com.hellotranslate.connector.jsonrpc.request.scope.SupportedProjectScopes.PATH_CHILDREN_REFERENCE;
import static com.hellotranslate.connector.jsonrpc.response.errors.JsonRpcErrors.METHOD_NOT_FOUND;

@Component
public class RequestExecutor {

    private final MetadataService metadataService;
    private final ContentService contentService;

    public RequestExecutor(MetadataService metadataService, ContentService contentService) {
        this.metadataService = metadataService;
        this.contentService = contentService;
    }

    public ResponseBody execute(RequestDto requestDto)
            throws InvalidMethodException, InvalidScopeException {
        return switch (requestDto.method()) {
            case ENTITY_GET -> executeEntityGetRequest(requestDto);
            case ENTITY_GET_BINARY -> executeGetBinaryContentRequest(requestDto);
            case ENTITY_CREATE -> executeUploadTranslationRequest(requestDto);
            default -> throw new InvalidMethodException(requestDto.id(), "No such method", METHOD_NOT_FOUND.code());
        };
    }

    private ResponseBody executeEntityGetRequest(RequestDto requestDto)
            throws InvalidScopeException {
        var scope = requestDto.params().requestParameters().getProjectionScopes()[0];
        return switch (scope) {
            case PATH_CHILDREN_ENTITY -> metadataService.getChildren(
                    requestDto.id(),
                    requestDto.params().config(),
                    requestDto.params().xdip());
            case PATH_CHILDREN_REFERENCE -> metadataService.getReferences(
                    requestDto.id(),
                    requestDto.params().config(),
                    requestDto.params().xdip());
            default -> throw new InvalidScopeException(requestDto.id(), "No such scope", NO_SUCH_SCOPE.code());
        };
    }

    private ResponseBody executeGetBinaryContentRequest(RequestDto requestDto) {
        return contentService.getContent(requestDto.id(), requestDto.params().config(), requestDto.params().xdip());
    }

    private ResponseBody executeUploadTranslationRequest(RequestDto requestDto) {
        return contentService.upload(
                requestDto.id(),
                requestDto.params().xdip(),
                requestDto.params().config(),
                requestDto.params().entity(),
                requestDto.params().binaryContents());
    }
}
