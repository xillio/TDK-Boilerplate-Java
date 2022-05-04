package nl.hellotranslate.connector.service;

import lombok.RequiredArgsConstructor;
import nl.hellotranslate.connector.jsonrpc.request.RequestDto;
import nl.hellotranslate.connector.jsonrpc.request.dtos.ConfigDto;
import nl.hellotranslate.connector.jsonrpc.request.dtos.EntityDto;
import nl.hellotranslate.connector.jsonrpc.response.LocHubErrorCodes;
import nl.hellotranslate.connector.jsonrpc.response.ResponseDto;
import nl.hellotranslate.connector.jsonrpc.response.ResponseDtoFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static nl.hellotranslate.connector.jsonrpc.response.LocHubErrorCodes.CONNECTOR_OPERATION_FAILED;

@Service
@RequiredArgsConstructor
public class TranslationService {

    private final ResponseDtoFactory responseFactory;

    public ResponseDto upload(String xdip, ConfigDto config, EntityDto entity, String binaryContents) //todo change to input stream
    {
        try {
            return null;
        } catch (Exception e) {
            return responseFactory.createErrorResponse(
                    xdip,
                    CONNECTOR_OPERATION_FAILED,
                    "Failed to upload translation",
                    Optional.of(e.getMessage())
            );
        }
    }
}
