package com.hellotranslate.connector.service;

import com.hellotranslate.connector.jsonrpc.request.RequestDto;
import com.hellotranslate.connector.jsonrpc.request.dtos.RequestParametersDto;
import com.hellotranslate.connector.model.XDIP;
import org.springframework.stereotype.Service;

import static com.hellotranslate.connector.jsonrpc.Method.*;
import static com.hellotranslate.connector.jsonrpc.ProtocolVersion.V2_0;

@Service
public class RequestBodyValidationService {

    private final ConfigValidationService configValidationService;

    public RequestBodyValidationService(ConfigValidationService configValidationService)
    {
        this.configValidationService = configValidationService;
    }

    public boolean validate(RequestDto requestDto)
    {
        return hasBasicAttributes(requestDto)
               && hasMethodAttributes(requestDto);
    }

    private boolean hasBasicAttributes(RequestDto requestDto)
    {
        return requestDto.id() != null
               && V2_0.equals(requestDto.jsonrpc())
               && METHODS.contains(requestDto.method())
               && requestDto.params() != null;
    }

    private boolean hasMethodAttributes(RequestDto requestDto)
    {
        return switch (requestDto.method()) {

            case ENTITY_GET -> configValidationService.validate(requestDto.params().getConfig())
                               && validateXdip(requestDto)
                               && validateRequestParameters(requestDto);

            case ENTITY_CREATE -> configValidationService.validate(requestDto.params().getConfig())
                                  && validateXdip(requestDto);

            case ENTITY_GET_BINARY -> configValidationService.validate(requestDto.params().getConfig())
                                      && validateRequestParameters(requestDto)
                                      && validateEntity(requestDto)
                                      && validateBinaryContents(requestDto);
            default -> false;
        };
    }

    private boolean validateBinaryContents(RequestDto requestDto)
    {
        return requestDto.params().getBinaryContents() != null;
    }

    private boolean validateEntity(RequestDto requestDto)
    {
        return requestDto.params().getEntity() != null;
    }

    private boolean validateRequestParameters(RequestDto requestDto)
    {
        return requestDto.params().getRequestParameters() != null
               && requestDto.params()
                            .getRequestParameters()
                            .getClass()
                            .equals(RequestParametersDto.class);
    }

    private boolean validateXdip(RequestDto requestDto)
    {
        return requestDto.params().getXdip() != null
               && requestDto.params().getXdip().getClass().equals(XDIP.class);
    }
}
