package nl.hellotranslate.connector.jsonrpc.request.scope;

import nl.hellotranslate.connector.jsonrpc.request.dtos.ConfigDto;
import nl.hellotranslate.connector.jsonrpc.response.ResponseDto;
import nl.hellotranslate.connector.service.MetadataService;

public class PathChildrenEntity implements ProjectionScope {

    @Override
    public ResponseDto getReferences(
            MetadataService metadataService,
            ConfigDto config,
            String xdip)
    {
        return metadataService.deliverMetadataChildrenEntity(config, xdip);
    }
}
