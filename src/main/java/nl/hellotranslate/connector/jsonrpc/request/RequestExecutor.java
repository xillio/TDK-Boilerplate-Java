package nl.hellotranslate.connector.jsonrpc.request;

import lombok.RequiredArgsConstructor;
import nl.hellotranslate.connector.jsonrpc.response.ResponseDto;
import nl.hellotranslate.connector.jsonrpc.response.ResponseDtoFactory;
import nl.hellotranslate.connector.service.ContentService;
import nl.hellotranslate.connector.service.MetadataService;
import nl.hellotranslate.connector.service.TranslationService;
import org.springframework.stereotype.Component;

import static nl.hellotranslate.connector.jsonrpc.Method.*;

@Component
@RequiredArgsConstructor
public class RequestExecutor {

    private final ResponseDtoFactory responseFactory;

    private final MetadataService metadataService;
    private final ContentService contentService;
    private final TranslationService translationService;

    public ResponseDto execute(RequestDto requestDto)
    {
        return switch (requestDto.method()) {
            case ENTITY_GET -> contentService.parseProjectionScopes(requestDto)
                                             .getReferences(
                                                     metadataService,
                                                     requestDto.params().getConfig(),
                                                     requestDto.params().getXdip());

            case ENTITY_GET_BINARY -> contentService.getContent(
                    requestDto.id(),
                    requestDto.params()
                              .getConfig(),
                    requestDto.params()
                              .getXdip());
            case ENTITY_CREATE -> translationService.upload(
                    requestDto.params().getXdip(),
                    requestDto.params().getConfig(),
                    requestDto.params().getEntity(),
                    requestDto.params()
                              .getBinaryContents());
            default -> responseFactory.getInvalidConfigurationResponse(requestDto.id());
        };
    }
}
