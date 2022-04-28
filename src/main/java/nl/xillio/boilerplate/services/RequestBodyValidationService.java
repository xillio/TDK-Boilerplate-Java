package nl.xillio.boilerplate.services;

import nl.xillio.boilerplate.http.HTTPMethod;
import nl.xillio.boilerplate.http.Version;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class RequestBodyValidationService {

    public boolean isValid(Map<String, Object> requestBody)
    {
        return requestBody.get("id") != null
               && requestBody.get("jsonrpc") == Version.V2_0
               && HTTPMethod.HTTP_METHODS.contains((String) requestBody.get("method"))
               && requestBody.get("params").getClass() == LinkedHashMap.class;
    }
}
