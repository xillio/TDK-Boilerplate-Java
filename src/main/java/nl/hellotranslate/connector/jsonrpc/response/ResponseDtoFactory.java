package nl.hellotranslate.connector.jsonrpc.response;

import nl.hellotranslate.connector.jsonrpc.response.components.Error;
import nl.hellotranslate.connector.jsonrpc.response.components.Result;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

import static nl.hellotranslate.connector.jsonrpc.ProtocolVersion.V2_0;
import static nl.hellotranslate.connector.jsonrpc.response.LocHubErrorCodes.INVALID_CONFIGURATION;

@Component
public class ResponseDtoFactory {

    public ResponseDto createErrorResponse(
            String contentId,
            int errorCode,
            String message,
            Optional<String> data)
    {
        return new ResponseDto(
                contentId,
                V2_0,
                new Error(
                        errorCode,
                        message,
                        data
                )
        );
    }

    public ResponseDto createSuccessResponse(
            String id,
            Object result)
    {
        return new ResponseDto(
                id,
                V2_0,
                new Result(
                        Map.of("result", result)
                )
        );
    }

    public ResponseDto getInvalidConfigurationResponse(String id)
    {
        return new ResponseDto(
                id,
                V2_0,
                new Error(
                        INVALID_CONFIGURATION,
                        "Invalid request body",
                        Optional.empty()
                )
        );
    }
}
