package nl.hellotranslate.connector.jsonrpc.request.scope;

import nl.hellotranslate.connector.jsonrpc.request.dtos.ConfigDto;
import nl.hellotranslate.connector.jsonrpc.response.ResponseDto;
import nl.hellotranslate.connector.service.MetadataService;

public interface ProjectionScope {

    ResponseDto getReferences(
            MetadataService metadataService,
            ConfigDto config,
            String xdip);
}
