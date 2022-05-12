package com.hellotranslate.connector.service;

import com.hellotranslate.connector.exception.jsonrpc.bodyvalidation.*;
import com.hellotranslate.connector.jsonrpc.request.RequestDto;
import com.hellotranslate.connector.jsonrpc.request.dtos.RequestParametersDto;
import com.hellotranslate.connector.model.decorators.OriginalDto;
import org.springframework.stereotype.Service;

import static com.hellotranslate.connector.exception.lochub.LocHubErrors.*;
import static com.hellotranslate.connector.jsonrpc.Method.*;
import static com.hellotranslate.connector.jsonrpc.ProtocolVersion.V2_0;
import static com.hellotranslate.connector.jsonrpc.response.errors.JsonRpcErrors.*;

@Service
public class RequestBodyValidationService {

    private final ConfigValidationService configValidationService;
    private final XdipValidationService xdipValidationService;

    public RequestBodyValidationService(ConfigValidationService configValidationService, XdipValidationService xdipValidationService) {
        this.configValidationService = configValidationService;
        this.xdipValidationService = xdipValidationService;
    }

    public void validate(RequestDto requestDto) {
        hasBasicAttributes(requestDto);
        hasMethodAttributes(requestDto);
        xdipIsValid(requestDto);
        methodSupported(requestDto);
    }

    private void xdipIsValid(RequestDto requestDto) {
        xdipValidationService.validate(requestDto);
    }

    private void methodSupported(RequestDto requestDto) {
        if (!METHODS.contains(requestDto.method())) {
            throw new InvalidMethodException(requestDto.id(), "Method is not supported", METHOD_NOT_FOUND.code());
        }
    }

    private void hasBasicAttributes(RequestDto requestDto) {
        if (basicAttributesInvalid(requestDto)) {
            throw new InvalidRequestBodyException(requestDto.id(), INVALID_REQUEST.message(), INVALID_REQUEST.code());
        }
    }

    private void hasMethodAttributes(RequestDto requestDto) {
        switch (requestDto.method()) {
            case ENTITY_GET -> {
                configValidationService.validate(requestDto.id(), requestDto.params().config());
                xdipIsPresent(requestDto);
                validateRequestParameters(requestDto);
            }

            case ENTITY_CREATE -> {
                configValidationService.validate(requestDto.id(), requestDto.params().config());
                validateRequestParameters(requestDto);
                validateEntity(requestDto);
                validateBinaryContents(requestDto);
            }

            case ENTITY_GET_BINARY -> {
                configValidationService.validate(requestDto.id(), requestDto.params().config());
                xdipIsPresent(requestDto);
            }

            default -> throw new InvalidRequestParameters(requestDto.id(), "Scope is empty or not supported", NO_SUCH_SCOPE.code());
        }
    }

    private void validateBinaryContents(RequestDto requestDto) {
        if (contentIsNotPresent(requestDto)) {
            throw new NoContentToUploadException(requestDto.id(), "Scope is empty or not supported", NO_BINARY_CONTENT.code());
        }
    }

    private void validateEntity(RequestDto requestDto) {
        if (entityIsInvalid(requestDto)) {
            throw new InvalidEntityException(requestDto.id(), "Entity is invalid", INVALID_PARAMS.code());
        }
    }

    private void validateRequestParameters(RequestDto requestDto) {
        if (requestParametersInvalid(requestDto)) {
            throw new InvalidRequestParameters(requestDto.id(), INVALID_PARAMS.message(), INVALID_PARAMS.code());
        }
    }

    private void xdipIsPresent(RequestDto requestDto) {
        var xdip = requestDto.params().xdip();
        if (xdip == null) {
            throw new InvalidXdipException(requestDto.id(), "Xdip is empty or invalid", CONNECTOR_OPERATION_FAILED.code());
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
                || requestParameters.getProjectionScopes()[0].isEmpty();
    }

    private boolean entityIsInvalid(RequestDto requestDto) {
        var entity = requestDto.params().entity();
        return entity == null
                || entity.original() == null
                || originalIsInvalid(entity.original());
    }

    private boolean originalIsInvalid(OriginalDto original) {
        return original.name() == null
                || original.language() == null
                || originalNameIsInvalid(original)
                || originalLanguageInvalid(original);
    }

    private boolean originalLanguageInvalid(OriginalDto original) {
        return original.language().tag() == null
                || original.language().translationOf() == null
                || original.language().tag().isEmpty()
                || original.language().translationOf().isEmpty();
    }

    private boolean originalNameIsInvalid(OriginalDto original) {
        return original.name().systemName() == null
                || original.name().displayName() == null
                || original.name().systemName().isEmpty();
    }

    private boolean contentIsNotPresent(RequestDto requestDto) {
        return requestDto.params().binaryContents() == null;
    }
}
