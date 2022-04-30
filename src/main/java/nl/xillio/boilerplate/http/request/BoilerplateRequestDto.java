package nl.xillio.boilerplate.http.request;

import nl.xillio.boilerplate.model.params.Params;

import java.util.UUID;

public record BoilerplateRequestDto(
        UUID id,
        String jsonrpc,
        String method,
        Params params) {

}
