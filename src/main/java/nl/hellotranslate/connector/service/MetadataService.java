package nl.hellotranslate.connector.service;

import lombok.RequiredArgsConstructor;
import nl.hellotranslate.connector.jsonrpc.request.dtos.ConfigDto;
import nl.hellotranslate.connector.jsonrpc.response.LocHubErrorCodes;
import nl.hellotranslate.connector.jsonrpc.response.ResponseDto;
import nl.hellotranslate.connector.jsonrpc.response.ResponseDtoFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MetadataService {

    private final ResponseDtoFactory responseFactory;

    public ResponseDto deliverMetadataChildrenReference(
            ConfigDto config,
            String xdip)
    {
        try {
            return null;
        } catch (Exception e) {
            return responseFactory.createErrorResponse(
                    xdip,
                    LocHubErrorCodes.CONNECTOR_OPERATION_FAILED,
                    "Failed to deliver metadata",
                    Optional.ofNullable(e.getMessage()));
        }
    }

    public ResponseDto deliverMetadataChildrenEntity(
            ConfigDto config,
            String xdip)
    {
        try {
            return null;
        } catch (Exception e) {
            return responseFactory.createErrorResponse(
                    xdip,
                    LocHubErrorCodes.CONNECTOR_OPERATION_FAILED,
                    "Failed to deliver metadata",
                    Optional.ofNullable(e.getMessage()));
        }
    }
}
