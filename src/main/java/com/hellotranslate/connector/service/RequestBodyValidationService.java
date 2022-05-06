package com.hellotranslate.connector.service;

import lombok.RequiredArgsConstructor;
import com.hellotranslate.connector.jsonrpc.request.RequestDto;
import org.springframework.stereotype.Service;

import static com.hellotranslate.connector.jsonrpc.Method.METHODS;
import static com.hellotranslate.connector.jsonrpc.ProtocolVersion.V2_0;

@Service
@RequiredArgsConstructor
public class RequestBodyValidationService {

    public boolean validate(RequestDto boilerplateRequestDto)
    {
        return hasBasicAttributes(boilerplateRequestDto)
               && hasMethodAttributes(boilerplateRequestDto);
    }

    private boolean hasBasicAttributes(RequestDto boilerplateRequestDto)
    {
        return boilerplateRequestDto.id() != null
               && V2_0.equals(boilerplateRequestDto.jsonrpc())
               && METHODS.contains(boilerplateRequestDto.method())
               && boilerplateRequestDto.params() != null;
    }

    private boolean hasMethodAttributes(RequestDto boilerplateRequestDto)
    {

        return true;
    }
}
