package com.hellotranslate.connector.service;

import com.hellotranslate.connector.jsonrpc.request.RequestDto;
import com.hellotranslate.connector.jsonrpc.request.scope.*;
import com.hellotranslate.connector.jsonrpc.response.ResponseDtoFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MetadataService {

    private final ResponseDtoFactory responseFactory;

    public ProjectionScope parseProjectionScopes(RequestDto requestDto)
    {
        var projectionScope = requestDto.params().getRequestParameters().getProjectionScopes();

        return switch (projectionScope[0]) {
            case SupportedProjectScopes.PATH_CHILDREN_REFERENCE -> new PathChildrenReference();
            case SupportedProjectScopes.PATH_CHILDREN_ENTITY -> new PathChildrenEntity();
            default -> new UnsupportedScope(responseFactory);
        };
    }
}
