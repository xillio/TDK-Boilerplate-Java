package com.hellotranslate.connector.service;

import com.hellotranslate.connector.jsonrpc.request.RequestDto;
import com.hellotranslate.connector.jsonrpc.request.scope.PathChildrenEntity;
import com.hellotranslate.connector.jsonrpc.request.scope.PathChildrenReference;
import com.hellotranslate.connector.jsonrpc.request.scope.ProjectionScope;
import com.hellotranslate.connector.jsonrpc.request.scope.UnsupportedScope;
import com.hellotranslate.connector.jsonrpc.response.ResponseDtoFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.hellotranslate.connector.jsonrpc.request.scope.SupportedProjectScopes.*;

@Service
@RequiredArgsConstructor
public class MetadataService {

    private final ResponseDtoFactory responseFactory;

    public ProjectionScope parseProjectionScopes(RequestDto requestDto)
    {
        var projectionScope = requestDto.params().getRequestParameters().getProjectionScopes();

        return switch (projectionScope[0]) {
            case PATH_CHILDREN_REFERENCE -> new PathChildrenReference();
            case PATH_CHILDREN_ENTITY -> new PathChildrenEntity();
            default -> new UnsupportedScope(responseFactory);
        };
    }
}
