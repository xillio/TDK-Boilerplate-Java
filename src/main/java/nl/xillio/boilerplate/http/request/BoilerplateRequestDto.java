package nl.xillio.boilerplate.http.request;

import nl.xillio.boilerplate.model.dtos.params.ParamsDto;

import java.util.UUID;

public record BoilerplateRequestDto(
        UUID id,
        String jsonrpc,
        String method,
        ParamsDto paramsDto) {

}
