package nl.xillio.boilerplate.service;

import nl.xillio.boilerplate.http.JsonRpcMethod;
import nl.xillio.boilerplate.http.Version;
import nl.xillio.boilerplate.http.request.BoilerplateRequestDto;
import org.springframework.stereotype.Service;

@Service
public class RequestBodyValidationService {

    public boolean isValid(BoilerplateRequestDto requestDto)
    {
        return requestDto.id() != null
               && Version.V2_0.equals(requestDto.jsonrpc())
               && JsonRpcMethod.METHODS.contains(requestDto.method())
               && requestDto.params() != null;
    }
}
