package com.hellotranslate.connector.service;

import com.hellotranslate.connector.model.DecoratorsContainer;
import com.hellotranslate.connector.exception.jsonrpc.bodyvalidation.*;
import com.hellotranslate.connector.jsonrpc.request.Request;
import com.hellotranslate.connector.jsonrpc.request.dtos.RequestParameters;
import org.springframework.stereotype.Service;

import static com.hellotranslate.connector.jsonrpc.Method.*;
import static com.hellotranslate.connector.jsonrpc.ProtocolVersion.V2_0;
import static com.hellotranslate.connector.jsonrpc.response.errors.JsonRpcErrors.*;

@Service
public class RequestBodyValidationService {

    private final ConfigValidationService configValidationService;

    public RequestBodyValidationService(ConfigValidationService configValidationService) {
        this.configValidationService = configValidationService;
    }

    public void validate(Request request) {
        hasBasicAttributes(request);
        hasMethodAttributes(request);
        methodSupported(request);
    }

    private void methodSupported(Request request) {
        if (!METHODS.contains(request.method())) {
            throw new InvalidMethodException(METHOD_NOT_FOUND.message());
        }
    }

    private void hasBasicAttributes(Request request) {
        if (basicAttributesInvalid(request)) {
            throw new InvalidRequestBodyException(INVALID_REQUEST.message());
        }
    }

    private void hasMethodAttributes(Request request) {
        switch (request.method()) {
            case ENTITY_GET -> {
                configValidationService.validate(request.params().config());
                xdipIsPresent(request);
                validateRequestParameters(request);
            }

            case ENTITY_CREATE -> {
                configValidationService.validate(request.params().config());
                validateRequestParameters(request);
                validateEntity(request);
                validateBinaryContents(request);
            }

            case ENTITY_GET_BINARY -> {
                configValidationService.validate(request.params().config());
                xdipIsPresent(request);
            }

            default -> throw new InvalidRequestParameters(METHOD_NOT_FOUND.message());
        }
    }

    private void validateBinaryContents(Request request) {
        if (contentIsNotPresent(request)) {
            throw new NoContentToUploadException("No content to upload");
        }
    }

    private void validateEntity(Request request) {
        if (entityIsInvalid(request)) {
            throw new InvalidEntityException("Entity is invalid");
        }
    }

    private void validateRequestParameters(Request request) {
        if (requestParametersInvalid(request)) {
            throw new InvalidRequestParameters(INVALID_PARAMS.message());
        }
    }

    private void xdipIsPresent(Request request) {
        var xdip = request.params().xdip();
        if (xdip == null) {
            throw new InvalidXdipException("Xdip is empty");
        }
    }

    private boolean basicAttributesInvalid(Request request) {
        return request.id() == null
                || !V2_0.equals(request.jsonrpc())
                || request.method() == null
                || request.params() == null;
    }

    private boolean requestParametersInvalid(Request request) {
        var requestParameters = request.params().requestParameters();
        return requestParameters == null
                || requestParameters.getClass() != RequestParameters.class
                || requestParameters.getProjectionScopes() == null
                || requestParameters.getProjectionScopes().length != 1
                || requestParameters.getProjectionScopes()[0].trim().isEmpty();
    }

    private boolean entityIsInvalid(Request request) {
        var entity = request.params().entity();
        return entity == null
                || entity.original() == null
                || originalIsInvalid(entity.original());
    }

    private boolean originalIsInvalid(DecoratorsContainer original) {
        return original.name() == null
                || original.language() == null
                || originalNameIsInvalid(original)
                || originalLanguageInvalid(original);
    }

    private boolean originalLanguageInvalid(DecoratorsContainer original) {
        return original.language().tag() == null
                || original.language().translationOf() == null
                || original.language().tag().isEmpty()
                || original.language().translationOf().isEmpty();
    }

    private boolean originalNameIsInvalid(DecoratorsContainer original) {
        return original.name().systemName() == null
                || original.name().displayName() == null
                || original.name().systemName().isEmpty();
    }

    private boolean contentIsNotPresent(Request request) {
        return request.params().binaryContents() == null;
    }
}
