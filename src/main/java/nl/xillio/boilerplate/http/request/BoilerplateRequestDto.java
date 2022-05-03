package nl.xillio.boilerplate.http.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;

import java.util.Map;

import static nl.xillio.boilerplate.http.request.fields.MandatoryRequestBodyFields.*;

@Getter
public class BoilerplateRequestDto extends BoilerplateRequestBody {

    private final Map<String, Object> params;

    @JsonCreator
    public BoilerplateRequestDto(
            String id,
            String jsonrpc,
            String method,
            Map<String, Object> params)
    {
        super(id, jsonrpc, method);
        this.params = params;
    }

    @SuppressWarnings("unchecked")
    @JsonCreator
    public BoilerplateRequestDto(Map<String, Object> requestBody)
    {
        super(
                (String) requestBody.get(ID),
                (String) requestBody.get(JSONRPC),
                (String) requestBody.get(METHOD)
        );
        this.params = new ObjectMapper().convertValue(requestBody.get(PARAMS), Map.class);
    }
}
