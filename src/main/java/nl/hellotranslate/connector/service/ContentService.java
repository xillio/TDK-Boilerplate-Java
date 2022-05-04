package nl.hellotranslate.connector.service;

import lombok.RequiredArgsConstructor;
import nl.hellotranslate.connector.jsonrpc.request.RequestDto;
import nl.hellotranslate.connector.jsonrpc.request.scope.EmptyScope;
import nl.hellotranslate.connector.jsonrpc.request.scope.PathChildrenEntity;
import nl.hellotranslate.connector.jsonrpc.request.scope.PathChildrenReference;
import nl.hellotranslate.connector.jsonrpc.request.scope.ProjectionScope;
import nl.hellotranslate.connector.jsonrpc.response.LocHubErrorCodes;
import nl.hellotranslate.connector.jsonrpc.response.ResponseDto;
import nl.hellotranslate.connector.jsonrpc.response.ResponseDtoFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static nl.hellotranslate.connector.jsonrpc.request.scope.SupportedProjectScopes.PATH_CHILDREN_ENTITY;
import static nl.hellotranslate.connector.jsonrpc.request.scope.SupportedProjectScopes.PATH_CHILDREN_REFERENCE;

@Service
@RequiredArgsConstructor
public class ContentService {

    private final ResponseDtoFactory responseFactory;

    public ResponseDto downloadBinaryContent(RequestDto requestDto)
    {
        try {
            return null;
        } catch (Exception e) {
            return responseFactory.createErrorResponse(
                    requestDto.id(),
                    LocHubErrorCodes.CONNECTOR_OPERATION_FAILED,
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
