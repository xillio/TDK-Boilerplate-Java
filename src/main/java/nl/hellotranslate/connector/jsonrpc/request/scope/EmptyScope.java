package nl.hellotranslate.connector.jsonrpc.request.scope;

import nl.hellotranslate.connector.jsonrpc.request.RequestDto;
import nl.hellotranslate.connector.jsonrpc.request.dtos.ConfigDto;
import nl.hellotranslate.connector.jsonrpc.response.LocHubErrorCodes;
import nl.hellotranslate.connector.jsonrpc.response.ResponseDto;
import nl.hellotranslate.connector.jsonrpc.response.ResponseDtoFactory;
import nl.hellotranslate.connector.service.MetadataService;

import java.util.Optional;

public record EmptyScope(ResponseDtoFactory responseFactory) implements ProjectionScope {

    @Override
    public ResponseDto getReferences(
            MetadataService metadataService,
            ConfigDto config,
            String id)
    {
        return responseFactory.createErrorResponse(
                id,
                LocHubErrorCodes.NO_SUCH_SCOPE,
                "Scope is empty or does not exist",
                Optional.empty());
    }
}
