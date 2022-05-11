package com.hellotranslate.connector.jsonrpc.response;

import com.hellotranslate.connector.exception.bodyvalidation.RequestBodyValidationException;
import com.hellotranslate.connector.jsonrpc.response.dtos.ErrorDto;
import com.hellotranslate.connector.jsonrpc.response.dtos.ResultDto;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.hellotranslate.connector.jsonrpc.ProtocolVersion.V2_0;

@Component
public class ResponseDtoFactory {

    public ResponseBody createErrorResponse(String id, int errorCode, Exception cause, Optional<String> data) {
        return new ErrorResponseBodyDto(id, V2_0, new ErrorDto(errorCode, cause.getMessage(), data));
    }

    public ResponseBody createSuccessResponse(String id, Object result) {
        return new ResultResponseBodyDto(id, V2_0, new ResultDto(result));
    }

    public ResponseBody createRequestBodyResponse(String id, RequestBodyValidationException cause) {
        return new ErrorResponseBodyDto(id, V2_0, new ErrorDto(cause.getErrorCode(), cause.getMessage()));
    }
}

