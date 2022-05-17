package com.hellotranslate.connector.service;

import com.hellotranslate.connector.decorators.Original;
import com.hellotranslate.connector.exception.jsonrpc.bodyvalidation.*;
import com.hellotranslate.connector.jsonrpc.request.RequestDto;
import com.hellotranslate.connector.jsonrpc.request.dtos.RequestParametersDto;
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

    public void validate(RequestDto requestDto) {
        hasBasicAttributes(requestDto);
        hasMethodAttributes(requestDto);
        methodSupported(requestDto);
    }

    private void methodSupported(RequestDto requestDto) {
        if (!METHODS.contains(requestDto.method())) {
            throw new InvalidMethodException(METHOD_NOT_FOUND.message());
        }
    }

    private void hasBasicAttributes(RequestDto requestDto) {
        if (basicAttributesInvalid(requestDto)) {
            throw new InvalidRequestBodyException(INVALID_REQUEST.message());
        }
    }

    private void hasMethodAttributes(RequestDto requestDto) {
        switch (requestDto.method()) {
            case ENTITY_GET -> {
                configValidationService.validate(requestDto.params().config());
                xdipIsPresent(requestDto);
                validateRequestParameters(requestDto);
            }

            case ENTITY_CREATE -> {
                configValidationService.validate(requestDto.params().config());
                validateRequestParameters(requestDto);
                validateEntity(requestDto);
                validateBinaryContents(requestDto);
            }

            case ENTITY_GET_BINARY -> {
                configValidationService.validate(requestDto.params().config());
                xdipIsPresent(requestDto);
            }

            default -> throw new InvalidRequestParameters(METHOD_NOT_FOUND.message());
        }
    }

    private void validateBinaryContents(RequestDto requestDto) {
        if (contentIsNotPresent(requestDto)) {
            throw new NoContentToUploadException("No content to upload");
        }
    }

    private void validateEntity(RequestDto requestDto) {
        if (entityIsInvalid(requestDto)) {
            throw new InvalidEntityException("Entity is invalid");
        }
    }

    private void validateRequestParameters(RequestDto requestDto) {
        if (requestParametersInvalid(requestDto)) {
            throw new InvalidRequestParameters(INVALID_PARAMS.message());
        }
    }

    private void xdipIsPresent(RequestDto requestDto) {
        var xdip = requestDto.params().xdip();
        if (xdip == null) {
            throw new InvalidXdipException("Xdip is empty");
        }
    }

    private boolean basicAttributesInvalid(RequestDto requestDto) {
        return requestDto.id() == null
                || !V2_0.equals(requestDto.jsonrpc())
                || requestDto.method() == null
                || requestDto.params() == null;
    }

    private boolean requestParametersInvalid(RequestDto requestDto) {
        var requestParameters = requestDto.params().requestParameters();
        return requestParameters == null
                || requestParameters.getClass() != RequestParametersDto.class
                || requestParameters.getProjectionScopes() == null
                || requestParameters.getProjectionScopes().length != 1
                || requestParameters.getProjectionScopes()[0].trim().isEmpty();
    }

    private boolean entityIsInvalid(RequestDto requestDto) {
        var entity = requestDto.params().entity();
        return entity == null
                || entity.original() == null
                || originalIsInvalid(entity.original());
    }

    private boolean originalIsInvalid(Original original) {
        return original.name() == null
                || original.language() == null
                || originalNameIsInvalid(original)
                || originalLanguageInvalid(original);
    }

    private boolean originalLanguageInvalid(Original original) {
        return original.language().tag() == null
                || original.language().translationOf() == null
                || original.language().tag().isEmpty()
                || original.language().translationOf().isEmpty();
    }

    private boolean originalNameIsInvalid(Original original) {
        return original.name().systemName() == null
                || original.name().displayName() == null
                || original.name().systemName().isEmpty();
    }

    private boolean contentIsNotPresent(RequestDto requestDto) {
        return requestDto.params().binaryContents() == null;
    }
}
