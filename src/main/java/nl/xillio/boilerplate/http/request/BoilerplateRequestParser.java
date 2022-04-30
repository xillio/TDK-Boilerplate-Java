package nl.xillio.boilerplate.http.request;

import lombok.RequiredArgsConstructor;
import nl.xillio.boilerplate.http.JsonRpcMethod;
import nl.xillio.boilerplate.http.response.BoilerplateResponseDto;
import nl.xillio.boilerplate.service.ContentService;
import nl.xillio.boilerplate.service.MetadataService;
import nl.xillio.boilerplate.service.TranslationService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BoilerplateRequestParser {

    private final MetadataService metadataService;
    private final ContentService contentService;
    private final TranslationService translationService;

    public BoilerplateResponseDto executeRequest(BoilerplateRequestDto requestDto)
    {
        return switch (requestDto.method()) {
            case JsonRpcMethod.ENTITY_GET -> contentService.parseProjectionScopes(requestDto).getReferences();
            case JsonRpcMethod.ENTITY_GET_BINARY -> contentService.downloadBinaryContent(requestDto.id());
            case JsonRpcMethod.ENTITY_CREATE -> translationService.upload(requestDto);
            case JsonRpcMethod.METADATA_DELIVER -> metadataService.deliver(requestDto);
        };
    }
}