package com.hellotranslate.connector.jsonrpc.response;

import com.hellotranslate.connector.jsonrpc.response.dtos.Error;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.hellotranslate.connector.jsonrpc.ProtocolVersion.V2_0;

@Component
public class ResponseDtoFactory {

    public ResponseBody createErrorResponse(String requestId, int errorCode, String message) {
        return new ErrorResponseBody(requestId, V2_0, new Error(errorCode, message));
    }

    public ResponseBody createErrorResponse(String requestId, int errorCode, String message, Optional<String> data) {
        return new ErrorResponseBody(requestId, V2_0, new Error(errorCode, message, data));
    }

    public ResponseBody createSuccessResponse(String requestId, Object result) {
        return new ResultResponseBody(requestId, V2_0, result);
    }
}

