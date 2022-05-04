package nl.hellotranslate.connector.service;

import lombok.RequiredArgsConstructor;
import nl.hellotranslate.connector.jsonrpc.response.LocHubErrorCodes;
import nl.hellotranslate.connector.jsonrpc.response.ResponseDtoFactory;
import nl.hellotranslate.connector.jsonrpc.request.RequestDto;
import nl.hellotranslate.connector.jsonrpc.response.ResponseDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MetadataService {

    private final ResponseDtoFactory responseFactory;

    public ResponseDto deliverMetadataChildrenReference(RequestDto requestDto)
    {
        try {
            return null;
        } catch (Exception e) {
            return responseFactory.createErrorResponse(
                    requestDto.id(),
                    LocHubErrorCodes.CONNECTOR_OPERATION_FAILED,
                    "Failed to deliver metadata",
                    Optional.ofNullable(e.getMessage()));
        }
    }

    public ResponseDto deliverMetadataChildrenEntity(RequestDto requestDto)
    {
        try {
            return null;
        } catch (Exception e) {
            return responseFactory.createErrorResponse(
                    requestDto.id(),
                    LocHubErrorCodes.CONNECTOR_OPERATION_FAILED,
                    "Failed to deliver metadata",
                    Optional.ofNullable(e.getMessage()));
        }
    }
}
