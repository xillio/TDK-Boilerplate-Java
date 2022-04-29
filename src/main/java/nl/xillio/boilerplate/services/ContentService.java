package nl.xillio.boilerplate.services;

import lombok.RequiredArgsConstructor;
import nl.xillio.boilerplate.http.request.BoilerplateRequestDto;
import nl.xillio.boilerplate.http.response.BoilerplateResponseDto;
import nl.xillio.boilerplate.http.response.BoilerplateResponseDtoFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContentService {

    private final RequestBodyValidationService validator;
    private final BoilerplateResponseDtoFactory responseFactory;

    public BoilerplateResponseDto download(UUID contentId)
    {
        try {
            // download content
            return responseFactory.createSuccessResponse(contentId);
        } catch (Exception e) {

            return null;
        }
    }

    public BoilerplateResponseDto upload(BoilerplateRequestDto requestDto)
    {

        try {
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
