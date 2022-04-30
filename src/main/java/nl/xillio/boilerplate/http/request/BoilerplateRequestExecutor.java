package nl.xillio.boilerplate.http.request;

import lombok.RequiredArgsConstructor;
import nl.xillio.boilerplate.http.JsonRpcMethod;
import nl.xillio.boilerplate.http.response.BoilerplateResponseDto;
import nl.xillio.boilerplate.http.response.BoilerplateResponseDtoFactory;
import nl.xillio.boilerplate.service.ContentService;
import nl.xillio.boilerplate.service.MetadataService;
import nl.xillio.boilerplate.service.TranslationService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BoilerplateRequestExecutor {

    private final BoilerplateResponseDtoFactory responseFactory;

    private final MetadataService metadataService;
    private final ContentService contentService;
    private final TranslationService translationService;

    public BoilerplateResponseDto execute(BoilerplateRequestDto requestDto) //todo consider to refactor
    {
        return switch (requestDto.method()) {
            case JsonRpcMethod.ENTITY_GET -> contentService.parseProjectionScopes(requestDto)
                                                           .getReferences(
                                                                   metadataService,
                                                                   requestDto);

            case JsonRpcMethod.ENTITY_GET_BINARY -> contentService.downloadBinaryContent(requestDto);
            case JsonRpcMethod.ENTITY_CREATE -> translationService.upload(requestDto);
            default -> responseFactory.getInvalidConfigurationResponse(requestDto.id());
        };
    }
}
