package nl.xillio.boilerplate.service;

import lombok.RequiredArgsConstructor;
import nl.xillio.boilerplate.http.request.BoilerplateRequestDto;
import nl.xillio.boilerplate.http.request.scope.PathChildrenEntity;
import nl.xillio.boilerplate.http.request.scope.PathChildrenReference;
import nl.xillio.boilerplate.http.request.scope.ProjectionScope;
import nl.xillio.boilerplate.http.response.BoilerplateResponseDto;
import nl.xillio.boilerplate.http.response.BoilerplateResponseDtoFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContentService {

    private final BoilerplateResponseDtoFactory responseFactory;

    public BoilerplateResponseDto downloadBinaryContent(UUID contentId)
    {
        try {
            // downloadBinaryContent content
            //return responseFactory.createSuccessResponse(contentId);
            return null;
        } catch (Exception e) {

            return null;
        }
    }

    public ProjectionScope parseProjectionScopes(BoilerplateRequestDto requestDto)
    {
        var projectionScope = requestDto.params()
                                        .requestParameters()
                                        .projectionScopes();

        if (projectionScope.length != 1) {
            throw new IllegalArgumentException("Only one projection scope is allowed"); // todo rewrite
        }

        return switch (projectionScope[0]) {
            case "path_children_reference" -> new PathChildrenReference();
            case "path_children_entity" -> new PathChildrenEntity();
            default -> throw new IllegalArgumentException("Unknown projection scope"); //todo rewrite
        };
    }
}
