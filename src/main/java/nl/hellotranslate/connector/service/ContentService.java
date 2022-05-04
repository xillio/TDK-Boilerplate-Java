package nl.hellotranslate.connector.service;

import lombok.RequiredArgsConstructor;
import nl.hellotranslate.connector.jsonrpc.request.RequestDto;
import nl.hellotranslate.connector.jsonrpc.request.dtos.ConfigDto;
import nl.hellotranslate.connector.jsonrpc.request.scope.EmptyScope;
import nl.hellotranslate.connector.jsonrpc.request.scope.PathChildrenEntity;
import nl.hellotranslate.connector.jsonrpc.request.scope.PathChildrenReference;
import nl.hellotranslate.connector.jsonrpc.request.scope.ProjectionScope;
import nl.hellotranslate.connector.jsonrpc.response.ResponseDto;
import nl.hellotranslate.connector.jsonrpc.response.ResponseDtoFactory;
import nl.hellotranslate.connector.repository.ContentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static nl.hellotranslate.connector.jsonrpc.request.scope.SupportedProjectScopes.PATH_CHILDREN_ENTITY;
import static nl.hellotranslate.connector.jsonrpc.request.scope.SupportedProjectScopes.PATH_CHILDREN_REFERENCE;
import static nl.hellotranslate.connector.jsonrpc.response.LocHubErrorCodes.NO_BINARY_CONTENT;

@Service
@RequiredArgsConstructor
public class ContentService {

    private final ResponseDtoFactory responseFactory;
    private final ContentRepository contentRepository;

    public ResponseDto getContent(
            String id,
            ConfigDto config,
            String xdip)
    {
        try {
            var binaryContent = contentRepository.downloadContent(xdip);
            return responseFactory.createSuccessResponse(
                    id,
                    binaryContent.readAllBytes());
        } catch (Exception e) {
            return responseFactory.createErrorResponse(
                    id,
                    NO_BINARY_CONTENT,
                    "Something went wrong during content download",
                    Optional.empty());
        }
    }

    public ProjectionScope parseProjectionScopes(RequestDto requestDto)
    {
        var projectionScope = requestDto.params().getRequestParameters().getProjectionScopes();

        return switch (projectionScope[0]) {
            case PATH_CHILDREN_REFERENCE -> new PathChildrenReference();
            case PATH_CHILDREN_ENTITY -> new PathChildrenEntity();
            default -> new EmptyScope(responseFactory);
        };
    }
}
