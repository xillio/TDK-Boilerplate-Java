package nl.xillio.boilerplate.service;

import lombok.RequiredArgsConstructor;
import nl.xillio.boilerplate.http.request.BoilerplateRequestDto;
import org.springframework.stereotype.Service;

import static nl.xillio.boilerplate.http.JsonRpcMethod.METHODS;
import static nl.xillio.boilerplate.http.JsonRpcProtocolVersion.V2_0;
import static nl.xillio.boilerplate.http.request.mapping.MethodToOptionalFieldMapping.METHOD_TO_BODY;

@Service
@RequiredArgsConstructor
public class RequestBodyValidationService {

    public boolean validate(BoilerplateRequestDto boilerplateRequestDto)
    {
        return hasBasicAttributes(boilerplateRequestDto)
               && hasAttributesForMethod(boilerplateRequestDto);
    }

    private boolean hasBasicAttributes(BoilerplateRequestDto boilerplateRequestDto)
    {
        return boilerplateRequestDto.getId() != null
               && V2_0.equals(boilerplateRequestDto.getJsonrpc())
               && METHODS.contains(boilerplateRequestDto.getMethod())
               && boilerplateRequestDto.getParams() != null;
    }

    private boolean hasAttributesForMethod(BoilerplateRequestDto boilerplateRequestDto)
    {
        var method = boilerplateRequestDto.getMethod();
        return METHOD_TO_BODY.get(method).equals(boilerplateRequestDto.getParams().keySet());
    }
}
