package nl.xillio.boilerplate.http.request.scope;

import nl.xillio.boilerplate.http.request.BoilerplateRequestDto;
import nl.xillio.boilerplate.http.response.BoilerplateResponseDto;
import nl.xillio.boilerplate.service.MetadataService;

public class PathChildrenReference implements ProjectionScope {

    @Override
    public BoilerplateResponseDto getReferences(
            MetadataService metadataService,
            BoilerplateRequestDto requestDto)
    {
        return metadataService.deliverMetadataChildrenReference(requestDto);
    }
}
