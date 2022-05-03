package nl.xillio.boilerplate.service;

import lombok.RequiredArgsConstructor;
import nl.xillio.boilerplate.http.request.BoilerplateRequestDto;
import nl.xillio.boilerplate.http.request.scope.PathChildrenEntity;
import nl.xillio.boilerplate.http.request.scope.PathChildrenReference;
import nl.xillio.boilerplate.http.request.scope.ProjectionScope;
import nl.xillio.boilerplate.http.response.BoilerplateResponseDto;
import nl.xillio.boilerplate.http.response.BoilerplateResponseDtoFactory;
import nl.xillio.boilerplate.http.response.ErrorCodes;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import static nl.xillio.boilerplate.http.request.fields.OptionalRequestBodyFields.PROJECTION_SCOPES;
import static nl.xillio.boilerplate.http.request.fields.OptionalRequestBodyFields.REQUEST_PARAMETERS;
import static nl.xillio.boilerplate.http.request.scope.SupportedProjectScopes.PATH_CHILDREN_ENTITY;
import static nl.xillio.boilerplate.http.request.scope.SupportedProjectScopes.PATH_CHILDREN_REFERENCE;

@Service
@RequiredArgsConstructor
public class ContentService {

    private final BoilerplateResponseDtoFactory responseFactory;

    public BoilerplateResponseDto downloadBinaryContent(BoilerplateRequestDto requestDto)
    {
        try {
            return null;
        } catch (Exception e) {
            return responseFactory.createErrorResponse(
                    requestDto.getId(),
                    ErrorCodes.CONNECTOR_OPERATION_FAILED,
                    "Something went wrong during content download",
                    Optional.empty());
        }
    }

    @SuppressWarnings("unchecked")
    public ProjectionScope parseProjectionScopes(BoilerplateRequestDto requestDto)
    {
        var projectionScope = (ArrayList<String>) (
                (HashMap<String, Object>) requestDto
                        .getParams()
                        .get(REQUEST_PARAMETERS)
        )
                .get(PROJECTION_SCOPES);

        if (projectionScope.size() != 1) {
            throw new IllegalArgumentException("Only one projection scope is allowed"); //todo rewrite
        }

        return switch (projectionScope.get(0)) {
            case PATH_CHILDREN_REFERENCE -> new PathChildrenReference();
            case PATH_CHILDREN_ENTITY -> new PathChildrenEntity();
            default -> throw new IllegalArgumentException("Unknown projection scope"); //todo rewrite
        };
    }
}
