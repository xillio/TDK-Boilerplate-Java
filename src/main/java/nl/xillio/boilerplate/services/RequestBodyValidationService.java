package nl.xillio.boilerplate.services;

import nl.xillio.boilerplate.http.HTTPMethod;
import nl.xillio.boilerplate.http.Version;
import nl.xillio.boilerplate.http.request.BoilerplateRequestDto;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

@Service
public class RequestBodyValidationService {

    public boolean isNotValid(BoilerplateRequestDto requestDto)
    {
        return requestDto.id() == null
               || !Version.V2_0.equals(requestDto.jsonrpc())
               || !HTTPMethod.HTTP_METHODS.contains(requestDto.method())
               || requestDto.params().getClass() != LinkedHashMap.class;
    }
}
