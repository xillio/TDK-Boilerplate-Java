package com.hellotranslate.connector.service;

import com.hellotranslate.connector.exception.bodyvalidation.*;
import com.hellotranslate.connector.jsonrpc.request.RequestDto;
import com.hellotranslate.connector.jsonrpc.request.dtos.OriginalDto;
import com.hellotranslate.connector.jsonrpc.request.dtos.RequestParametersDto;
import com.hellotranslate.connector.model.XDIP;
import org.springframework.stereotype.Service;

import static com.hellotranslate.connector.jsonrpc.Method.*;
import static com.hellotranslate.connector.jsonrpc.ProtocolVersion.V2_0;
import static com.hellotranslate.connector.jsonrpc.request.entity.SupportedKinds.SUPPORTED_KINDS;
import static com.hellotranslate.connector.jsonrpc.request.scope.SupportedProjectScopes.ENTITY;
import static com.hellotranslate.connector.jsonrpc.response.LocHubErrorCodes.*;

@Service
public class RequestBodyValidationService {

    private final ConfigValidationService configValidationService;

    public RequestBodyValidationService(ConfigValidationService configValidationService)
    {
        this.configValidationService = configValidationService;
    }

    public void validate(RequestDto requestDto)
            throws InvalidRequestBodyException, InvalidXdipException,
            InvalidRequestParameters, InvalidConfigException,
            NoContentToUploadException, InvalidEntityException
    {
        hasBasicAttributes(requestDto);
        hasMethodAttributes(requestDto);
    }

    private void hasBasicAttributes(RequestDto requestDto)
            throws InvalidRequestBodyException
    {
        if (basicAttributesInvalid(requestDto)) {
            throw new InvalidRequestBodyException("Invalid request body", MISSING_DECORATOR);
        }
    }

    private void hasMethodAttributes(RequestDto requestDto)
            throws InvalidXdipException, InvalidConfigException,
            InvalidRequestParameters, InvalidEntityException, NoContentToUploadException
    {
        switch (requestDto.method()) {
            case ENTITY_GET -> {
                configValidationService.validate(requestDto.params().getConfig());
                validateXdip(requestDto);
                validateRequestParameters(requestDto);
            }

            case ENTITY_CREATE -> {
                configValidationService.validate(requestDto.params().getConfig());
                validateRequestParameters(requestDto);
                validateEntity(requestDto);
                validateBinaryContents(requestDto);
            }

            case ENTITY_GET_BINARY -> {
                configValidationService.validate(requestDto.params().getConfig());
                validateXdip(requestDto);
            }

            default -> throw new InvalidRequestParameters("Scope is empty or not supported", NO_SUCH_SCOPE);
        }
    }

    private void validateBinaryContents(RequestDto requestDto)
            throws NoContentToUploadException
    {
        if (contentIsNotPresent(requestDto)) {
            throw new NoContentToUploadException("Binary content is not present", NO_BINARY_CONTENT);
        }
    }

    private void validateEntity(RequestDto requestDto)
            throws InvalidEntityException
    {
        if (entityIsInvalid(requestDto)) {
            throw new InvalidEntityException("Entity is invalid", NO_SUCH_ENTITY);
        }
    }

    private void validateRequestParameters(RequestDto requestDto)
            throws InvalidRequestParameters
    {
        if (requestParametersInvalid(requestDto)) {
            throw new InvalidRequestParameters("Request parameters are invalid", MISSING_DECORATOR);
        }
    }

    private void validateXdip(RequestDto requestDto)
            throws InvalidXdipException
    {
        var xdip = requestDto.params().getXdip();
        if (xdip == null || !xdip.getClass().equals(XDIP.class)) {
            throw new InvalidXdipException("Xdip is invalid", CONNECTOR_OPERATION_FAILED);
        }
    }

    private boolean basicAttributesInvalid(RequestDto requestDto)
    {
        return requestDto.id() == null
               && !V2_0.equals(requestDto.jsonrpc())
               && !METHODS.contains(requestDto.method())
               && requestDto.params() == null;
    }

    private boolean requestParametersInvalid(RequestDto requestDto)
    {
        var requestParameters = requestDto.params().getRequestParameters();

        return requestParameters == null
               || requestParameters.getClass() != RequestParametersDto.class
               || switch (requestDto.method()) {
            case ENTITY_GET -> requestParameters.getProjectionScopes() == null
                               || requestParameters.getProjectionScopes()[0].isEmpty()
                               || requestParameters.getProjectionIncludes() == null
                               || requestParameters.getProjectionExcludes() == null;

            case ENTITY_CREATE -> requestParameters.getProjectionScopes() == null
                                  || !requestParameters.getProjectionScopes()[0].equals(ENTITY);
            default -> false;
        };
    }

    private boolean entityIsInvalid(RequestDto requestDto)
    {
        var entity = requestDto.params().getEntity();
        return entity == null
               || !SUPPORTED_KINDS.contains(entity.kind())
               || entity.original() == null
               || originalIsInvalid(entity.original());
    }

    private boolean originalIsInvalid(OriginalDto original)
    {
        return original.name() == null
               || original.language() == null
               || originalNameIsInvalid(original)
               || originalLanguageInvalid(original);
    }

    private boolean originalLanguageInvalid(OriginalDto original)
    {
        return original.language().tag() == null
               || original.language().translationOf() == null;
    }

    private boolean originalNameIsInvalid(OriginalDto original)
    {
        return original.name().systemName() == null
               || original.name().displayName() == null
               || original.name().systemName().isEmpty()
               || original.name().displayName().isEmpty();
    }

    private boolean contentIsNotPresent(RequestDto requestDto)
    {
        return requestDto.params().getBinaryContents() == null;
    }
}
