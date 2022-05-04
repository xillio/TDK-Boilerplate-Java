package nl.hellotranslate.connector.jsonrpc.request;

import nl.hellotranslate.connector.jsonrpc.request.dtos.ParamsDto;

public record RequestDto(
        String id,
        String jsonrpc,
        String method,
        ParamsDto params) {

}
