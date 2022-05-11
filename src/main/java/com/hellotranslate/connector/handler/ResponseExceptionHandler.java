package com.hellotranslate.connector.handler;

import com.hellotranslate.connector.exception.jsonrpc.response.ResponseBodyException;
import com.hellotranslate.connector.jsonrpc.response.ResponseBody;
import com.hellotranslate.connector.jsonrpc.response.ResponseDtoFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Optional;

@RestControllerAdvice
public class ResponseExceptionHandler {

    private final ResponseDtoFactory responseFactory;

    public ResponseExceptionHandler(ResponseDtoFactory responseFactory) {
        this.responseFactory = responseFactory;
    }

    @ExceptionHandler(value = ResponseBodyException.class)
    public ResponseBody handleResponseBodyException(ResponseBodyException e) {
        return responseFactory.createErrorResponse(
                e.getRequestId(), e.getErrorCode(), e.getMessage(), Optional.of(e.getStackTrace()[0].getMethodName())
        );
    }
}
