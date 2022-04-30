package nl.xillio.boilerplate.service;

import lombok.RequiredArgsConstructor;
import nl.xillio.boilerplate.http.request.BoilerplateRequestDto;
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
        return null;
    }
}
