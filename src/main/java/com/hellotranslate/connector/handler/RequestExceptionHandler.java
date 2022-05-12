package com.hellotranslate.connector.handler;

import com.hellotranslate.connector.exception.jsonrpc.bodyvalidation.RequestBodyValidationException;
import com.hellotranslate.connector.jsonrpc.response.ResponseBody;
import com.hellotranslate.connector.jsonrpc.response.ResponseDtoFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RequestExceptionHandler {

    private final ResponseDtoFactory responseFactory;

    public RequestExceptionHandler(ResponseDtoFactory responseFactory) {
        this.responseFactory = responseFactory;
    }

    @ExceptionHandler(value = RequestBodyValidationException.class)
    public ResponseBody handleResponseBodyException(RequestBodyValidationException e) {
        return responseFactory.createErrorResponse(
                e.getRequestId(), e.getErrorCode(), e.getMessage()
        );
    }
}
