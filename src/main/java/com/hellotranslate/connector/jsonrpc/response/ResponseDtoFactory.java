package com.hellotranslate.connector.jsonrpc.response;

import com.hellotranslate.connector.jsonrpc.response.dtos.ErrorDto;
import com.hellotranslate.connector.jsonrpc.response.dtos.ResultDto;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.hellotranslate.connector.jsonrpc.ProtocolVersion.V2_0;

@Component
public class ResponseDtoFactory {

    public ResponseBody createErrorResponse(int errorCode, String message) {
        return new ErrorResponseBodyDto(V2_0, new ErrorDto(errorCode, message));
    }

    public ResponseBody createErrorResponse(int errorCode, String message, Optional<String> data) {
        return new ErrorResponseBodyDto(V2_0, new ErrorDto(errorCode, message, data));
    }

    public ResponseBody createSuccessResponse(String requestId, Object result) {
        return new ResultResponseBodyDto(requestId, V2_0, new ResultDto(result));
    }
}

