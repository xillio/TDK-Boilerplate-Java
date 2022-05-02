package nl.xillio.boilerplate.service;

import lombok.RequiredArgsConstructor;
import nl.xillio.boilerplate.http.request.BoilerplateRequestDto;
import org.springframework.stereotype.Service;

import static nl.xillio.boilerplate.http.JsonRpcMethod.METHODS;
import static nl.xillio.boilerplate.http.JsonRpcProtocolVersion.V2_0;

@Service
@RequiredArgsConstructor
public class RequestBodyValidationService {

    public boolean hasBasicAttributes(BoilerplateRequestDto boilerplateRequestDto)
    {
        // todo implement deep validation

        return boilerplateRequestDto.getId() != null
               && V2_0.equals(boilerplateRequestDto.getJsonrpc())
               && METHODS.contains(boilerplateRequestDto.getMethod())
               && boilerplateRequestDto.getParams() != null;
    }
}
