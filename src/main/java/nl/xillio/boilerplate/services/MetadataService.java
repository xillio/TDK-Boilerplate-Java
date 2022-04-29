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

    public BoilerplateResponseDto deliver(BoilerplateRequestDto requestDto)
    {
        try {
            // Upload the metadata

            return responseFactory.createSuccessResponse(
                    requestDto.id());
        } catch (Exception e) {
            return responseFactory.createErrorResponse(
                    requestDto.id(),
                    ErrorCodes.CONNECTOR_OPERATION_FAILED,
                    "Failed to deliver metadata",
                    Optional.ofNullable(e.getMessage()));
        }
    }
}
