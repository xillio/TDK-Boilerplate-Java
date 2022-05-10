package com.hellotranslate.connector.jsonrpc.response;

import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.hellotranslate.connector.jsonrpc.ProtocolVersion.V2_0;
import static com.hellotranslate.connector.jsonrpc.response.LocHubErrorCodes.INVALID_CONFIGURATION;

@Component
public class ResponseDtoFactory {

    public ResponseBody createErrorResponse(
            String id,
            int errorCode,
            Exception cause,
            Optional<String> data)
    {
        return new ErrorResponseBodyDto(id, V2_0, new ErrorDto(errorCode, cause.getMessage(), data));
    }

    public ResponseBody createSuccessResponse(
            String id,
            Object result)
    {
        return new ResultResponseBodyDto(id, V2_0, new ResultDto(result));
    }

    public ResponseBody createInvalidConfigurationResponse(String id)
    {
        return new ErrorResponseBodyDto(id, V2_0, new ErrorDto(INVALID_CONFIGURATION, "Invalid configuration")); //todo: add more details
    }
}
