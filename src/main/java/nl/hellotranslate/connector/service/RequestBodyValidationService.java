package nl.hellotranslate.connector.service;

import lombok.RequiredArgsConstructor;
import nl.hellotranslate.connector.jsonrpc.request.RequestDto;
import org.springframework.stereotype.Service;

import static nl.hellotranslate.connector.jsonrpc.Method.METHODS;
import static nl.hellotranslate.connector.jsonrpc.ProtocolVersion.V2_0;

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
