package com.hellotranslate.connector.service;

import com.hellotranslate.connector.jsonrpc.request.RequestDto;
import com.hellotranslate.connector.jsonrpc.request.dtos.RequestParametersDto;
import com.hellotranslate.connector.model.XDIP;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.hellotranslate.connector.jsonrpc.Method.*;
import static com.hellotranslate.connector.jsonrpc.ProtocolVersion.V2_0;

@Service
@RequiredArgsConstructor
public class RequestBodyValidationService {

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

            case ENTITY_GET -> validateConfig(requestDto)
                               && validateXdip(requestDto)
                               && validateRequestParameters(requestDto);

            case ENTITY_CREATE -> validateConfig(requestDto)
                                  && validateXdip(requestDto);

            case ENTITY_GET_BINARY -> validateConfig(requestDto)
                                      && validateRequestParameters(requestDto)
                                      && validateEntity(requestDto)
                                      && validateBinaryContents(requestDto);
            default -> false;
        };
    }

    //todo enhance
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

    private boolean validateConfig(RequestDto requestDto)
    {
        return requestDto.params().getConfig() != null;
    }
}
