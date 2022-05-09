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
            Map<String, Object> config,
            Exception cause,
            Optional<String> data)
    {
        var methodName = cause.getStackTrace()[0].getMethodName();
        var className = cause.getStackTrace()[0].getClassName()
                                                .substring(
                                                        cause.getStackTrace()[0]
                                                                .getClassName()
                                                                .lastIndexOf(".") + 1
                                                );

        return new ResponseDto(
                id,
                V2_0,
                config,
                new Error(
                        errorCode,
                        String.format(
                                "%s() method in %s is not implemented!",
                                methodName,
                                className),
                        data
                )
        );
    }

    public ResponseDto createSuccessResponse(
            String id,
            Map<String, Object> config,
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
            Map<String, Object> config)

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
