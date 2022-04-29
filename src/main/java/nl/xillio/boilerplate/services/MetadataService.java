package nl.xillio.boilerplate.services;

import lombok.RequiredArgsConstructor;
import nl.xillio.boilerplate.http.request.BoilerplateRequestDto;
import nl.xillio.boilerplate.http.response.BoilerplateResponseDto;
import nl.xillio.boilerplate.http.response.BoilerplateResponseDtoFactory;
import nl.xillio.boilerplate.http.response.ErrorCodes;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MetadataService {

    private final RequestBodyValidationService validator;
    private final BoilerplateResponseDtoFactory responseFactory;

    public BoilerplateResponseDto deliver(
            UUID contentId,
            BoilerplateRequestDto requestDto)
    {
        if (validator.isNotValid(requestDto)) {
            return responseFactory.createErrorResponse(
                    contentId,
                    ErrorCodes.INVALID_CONFIGURATION,
                    "Invalid request body",
                    Optional.empty());
        }

        try {
            // Upload the metadata

            return responseFactory.createSuccessResponse(
                    contentId);
        } catch (Exception e) {
            return responseFactory.createErrorResponse(
                    contentId,
                    ErrorCodes.CONNECTOR_OPERATION_FAILED,
                    "Failed to deliver metadata",
                    Optional.ofNullable(e.getMessage()));
        }
    }
}
