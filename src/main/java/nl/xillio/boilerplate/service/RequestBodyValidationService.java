package nl.xillio.boilerplate.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import nl.xillio.boilerplate.http.JsonRpcMethod;
import nl.xillio.boilerplate.http.Version;
import org.springframework.stereotype.Service;

import java.util.Map;

import static nl.xillio.boilerplate.http.request.fields.MandatoryRequestBodyFields.*;

@Service
@RequiredArgsConstructor
public class RequestBodyValidationService {

    public boolean hasBasicAttributes(Map<String, Object> requestDto)
    {
        var method = new ObjectMapper().convertValue(requestDto.get(METHOD), String.class);

        return requestDto.get(ID) != null
               && Version.V2_0.equals(requestDto.get(JSONRPC))
               && JsonRpcMethod.METHODS.contains(method)
               && requestDto.get(PARAMS) != null;
    }
}
