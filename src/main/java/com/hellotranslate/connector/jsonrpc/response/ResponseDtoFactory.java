package com.hellotranslate.connector.jsonrpc.response;

import com.hellotranslate.connector.jsonrpc.request.dtos.ConfigDto;
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
            ConfigDto config,
            String message,
            Optional<String> data)
    {
        return new ResponseDto(
                id,
                V2_0,
                config,
                new Error(
                        errorCode,
                        message,
                        data
                )
        );
    }

    public ResponseDto createSuccessResponse(
            String id,
            ConfigDto config,
            Object result)
    {
        return new ResponseDto(
                id,
                V2_0,
                config,
                new Result(
                        Map.of("result", result)
                )
        );
    }

    public ResponseDto createInvalidConfigurationResponse(
            String id,
            ConfigDto config)

    {
        return new ResponseDto(
                id,
                V2_0,
                config,
                new Error(
                        INVALID_CONFIGURATION,
                        "Invalid request body",
                        Optional.empty()
                )
        );
    }
}
