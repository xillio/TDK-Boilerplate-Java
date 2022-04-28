package nl.xillio.boilerplate.http.request;

import java.util.Map;
import java.util.UUID;

public record BoilerplateRequestDto(UUID id, String jsonrpc,
                                    String method,
                                    Map<String, Object> params) {

}
