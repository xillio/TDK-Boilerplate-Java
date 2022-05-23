package com.hellotranslate.connector.jsonrpc.request;

import com.hellotranslate.connector.exception.jsonrpc.bodyvalidation.InvalidMethodException;
import com.hellotranslate.connector.exception.jsonrpc.bodyvalidation.InvalidScopeException;
import com.hellotranslate.connector.jsonrpc.response.ResponseBody;
import com.hellotranslate.connector.service.ContentService;
import com.hellotranslate.connector.service.MetadataService;
import org.springframework.stereotype.Component;

import static com.hellotranslate.connector.exception.lochub.LocHubErrors.NO_SUCH_SCOPE;
import static com.hellotranslate.connector.jsonrpc.Method.*;
import static com.hellotranslate.connector.jsonrpc.request.scope.SupportedProjectScopes.*;
import static com.hellotranslate.connector.jsonrpc.response.errors.JsonRpcErrors.METHOD_NOT_FOUND;

@Component
public class RequestExecutor {

    private final MetadataService metadataService;
    private final ContentService contentService;

    public RequestExecutor(MetadataService metadataService, ContentService contentService) {
        this.metadataService = metadataService;
        this.contentService = contentService;
    }

    public ResponseBody execute(Request request) {
        return switch (request.method()) {
            case ENTITY_GET -> executeEntityGetRequest(request);
            case ENTITY_GET_BINARY -> executeGetBinaryContentRequest(request);
            case ENTITY_CREATE -> executeUploadTranslationRequest(request);
            default -> throw new InvalidMethodException(METHOD_NOT_FOUND.message());
        };
    }

    private ResponseBody executeEntityGetRequest(Request request) {
        var scope = request.params().requestParameters().getProjectionScopes()[0];
        return switch (scope) {
            case PATH_CHILDREN_ENTITY -> metadataService.getChildren(
                    request.id(),
                    request.params().config(),
                    request.params().xdip());
            case PATH_CHILDREN_REFERENCE -> metadataService.getReferences(
                    request.id(),
                    request.params().config(),
                    request.params().xdip());
            case ENTITY -> metadataService.getEntity(
                    request.id(),
                    request.params().config(),
                    request.params().xdip());
            default -> throw new InvalidScopeException(NO_SUCH_SCOPE.message());
        };
    }

    private ResponseBody executeGetBinaryContentRequest(Request request) {
        return contentService.getContent(request.id(), request.params().config(), request.params().xdip());
    }

    private ResponseBody executeUploadTranslationRequest(Request request) {
        return contentService.upload(
                request.id(),
                request.params().xdip(),
                request.params().config(),
                request.params().entity(),
                request.params().binaryContents());
    }
}
