package nl.hellotranslate.connector.jsonrpc.request.scope;

import nl.hellotranslate.connector.jsonrpc.request.RequestDto;
import nl.hellotranslate.connector.jsonrpc.response.ResponseDto;
import nl.hellotranslate.connector.service.MetadataService;

public class PathChildrenReference implements ProjectionScope {

    @Override
    public ResponseDto getReferences(
            MetadataService metadataService,
            RequestDto requestDto)
    {
        return metadataService.deliverMetadataChildrenReference(requestDto);
    }
}
