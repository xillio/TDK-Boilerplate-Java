package nl.hellotranslate.connector.jsonrpc.request.scope;

import nl.hellotranslate.connector.service.MetadataService;
import nl.hellotranslate.connector.jsonrpc.request.RequestDto;
import nl.hellotranslate.connector.jsonrpc.response.ResponseDto;

public interface ProjectionScope {

    ResponseDto getReferences(
            MetadataService metadataService,
            RequestDto requestDto);
}
