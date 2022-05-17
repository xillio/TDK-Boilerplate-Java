package com.hellotranslate.connector.handler;

import com.hellotranslate.connector.exception.jsonrpc.response.ResponseBodyException;
import com.hellotranslate.connector.jsonrpc.request.RequestDto;
import com.hellotranslate.connector.jsonrpc.response.ResponseBody;
import com.hellotranslate.connector.jsonrpc.response.ResponseDtoFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestControllerAdvice
public class ResponseExceptionHandler {

    private final ResponseDtoFactory responseFactory;

    public ResponseExceptionHandler(ResponseDtoFactory responseFactory) {
        this.responseFactory = responseFactory;
    }

    @ExceptionHandler(value = ResponseBodyException.class)
    public ResponseBody handleResponseBodyException(ResponseBodyException e, HttpServletRequest request) {
        var requestDto = (RequestDto) request.getAttribute("requestDto");
        return responseFactory.createErrorResponse(requestDto.id(), e.getErrorCode(), e.getMessage(), Optional.of(e.getStackTrace()[0].getMethodName()));
    }
}
