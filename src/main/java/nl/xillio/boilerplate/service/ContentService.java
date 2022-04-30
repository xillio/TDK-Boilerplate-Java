package nl.xillio.boilerplate.service;

import lombok.RequiredArgsConstructor;
import nl.xillio.boilerplate.http.request.BoilerplateRequestDto;
import nl.xillio.boilerplate.http.request.scope.PathChildrenEntity;
import nl.xillio.boilerplate.http.request.scope.PathChildrenReference;
import nl.xillio.boilerplate.http.request.scope.ProjectionScope;
import nl.xillio.boilerplate.http.response.BoilerplateResponseDto;
import nl.xillio.boilerplate.http.response.BoilerplateResponseDtoFactory;
import nl.xillio.boilerplate.repository.ContentRepository;
import org.springframework.stereotype.Service;

import static nl.xillio.boilerplate.http.request.scope.SupportedProjectScopes.PATH_CHILDREN_ENTITY;
import static nl.xillio.boilerplate.http.request.scope.SupportedProjectScopes.PATH_CHILDREN_REFERENCE;

@Service
@RequiredArgsConstructor
public class ContentService {

    private final BoilerplateResponseDtoFactory responseFactory;
    private final ContentRepository contentRepository;

    public BoilerplateResponseDto downloadBinaryContent(BoilerplateRequestDto requestDto)
    {
        try {
            // downloadBinaryContent content
            var content = contentRepository.getOneById(requestDto.params().xdip());
            return responseFactory.createSuccessResponse(requestDto, content);
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
            case PATH_CHILDREN_REFERENCE -> new PathChildrenReference();
            case PATH_CHILDREN_ENTITY -> new PathChildrenEntity();
            default -> throw new IllegalArgumentException("Unknown projection scope"); //todo rewrite
        };
    }
}
