package nl.xillio.boilerplate.service;

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

    private final BoilerplateResponseDtoFactory responseFactory;

    public BoilerplateResponseDto deliverMetadataChildrenReference(BoilerplateRequestDto requestDto)
    {
        try {
            return null;
           /* return responseFactory.createSuccessResponse(
                    requestDto.id(), new MetadataDto(childrenReference));*/
        } catch (Exception e) {
            return responseFactory.createErrorResponse(
                    requestDto.getId(),
                    ErrorCodes.CONNECTOR_OPERATION_FAILED,
                    "Failed to deliver metadata",
                    Optional.ofNullable(e.getMessage()));
        }
    }

    public BoilerplateResponseDto deliverMetadataChildrenEntity(BoilerplateRequestDto requestDto)
    {
        try {
          /*  var childrenEntity = metadataRepository.getChildrenEntity(requestDto.paramsDto()
                                                                                .xdip());
            return responseFactory.createSuccessResponse(requestDto.id(), childrenEntity);*/
            return null;
        } catch (Exception e) {
            return responseFactory.createErrorResponse(
                    requestDto.getId(),
                    ErrorCodes.CONNECTOR_OPERATION_FAILED,
                    "Failed to deliver metadata",
                    Optional.ofNullable(e.getMessage()));
        }
    }
}
