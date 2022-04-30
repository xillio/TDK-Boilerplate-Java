package nl.xillio.boilerplate.service;

import lombok.RequiredArgsConstructor;
import nl.xillio.boilerplate.http.request.BoilerplateRequestDto;
import nl.xillio.boilerplate.http.response.BoilerplateResponseDto;
import nl.xillio.boilerplate.http.response.BoilerplateResponseDtoFactory;
import nl.xillio.boilerplate.http.response.ErrorCodes;
import nl.xillio.boilerplate.repository.MetadataRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MetadataService {

    private final BoilerplateResponseDtoFactory responseFactory;
    private final MetadataRepository metadataRepository;

    public BoilerplateResponseDto deliverMetadataChildrenReference(BoilerplateRequestDto requestDto)
    {
        try {
            var childrenReference = metadataRepository.getChildrenReference(
                    requestDto.params().xdip()
            );
            return responseFactory.createSuccessResponse(requestDto.id(), childrenReference);
        } catch (Exception e) {
            return responseFactory.createErrorResponse(
                    requestDto.id(),
                    ErrorCodes.CONNECTOR_OPERATION_FAILED,
                    "Failed to deliver metadata",
                    Optional.ofNullable(e.getMessage()));
        }
    }

    public BoilerplateResponseDto deliverMetadataChildrenEntity(BoilerplateRequestDto requestDto)
    {
        try {
            var childrenEntity = metadataRepository.getChildrenEntity(requestDto.params().xdip());
            return responseFactory.createSuccessResponse(requestDto.id(), childrenEntity);
        } catch (Exception e) {
            return responseFactory.createErrorResponse(
                    requestDto.id(),
                    ErrorCodes.CONNECTOR_OPERATION_FAILED,
                    "Failed to deliver metadata",
                    Optional.ofNullable(e.getMessage()));
        }
    }
}
