package com.hellotranslate.connector.jsonrpc.response;

import com.hellotranslate.connector.jsonrpc.response.components.Error;
import com.hellotranslate.connector.jsonrpc.response.components.Result;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

import static com.hellotranslate.connector.jsonrpc.ProtocolVersion.V2_0;
import static com.hellotranslate.connector.jsonrpc.response.LocHubErrorCodes.INVALID_CONFIGURATION;

@Component
public class ResponseDtoFactory {

    public ResponseDto createErrorResponse(
            String id,
            int errorCode,
            String message,
            Optional<String> data)
    {
        return new ResponseDto(
                id,
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

    public ResponseDto createInvalidConfigurationResponse(String id)
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
