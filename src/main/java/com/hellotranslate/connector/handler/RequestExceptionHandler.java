package com.hellotranslate.connector.handler;

import com.hellotranslate.connector.exception.jsonrpc.bodyvalidation.RequestBodyValidationException;
import com.hellotranslate.connector.exception.jsonrpc.response.ResponseBodyException;
import com.hellotranslate.connector.jsonrpc.response.ResponseBody;
import com.hellotranslate.connector.jsonrpc.response.ResponseDtoFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RequestExceptionHandler {

    private final ResponseDtoFactory responseFactory;

    public RequestExceptionHandler(ResponseDtoFactory responseFactory) {
        this.responseFactory = responseFactory;
    }

    @ExceptionHandler(value = RequestBodyValidationException.class)
    public void handleResponseBodyException(ResponseBodyException e) {
        System.out.println(e.getErrorCode());
        System.out.println(e.getMessage());
        System.out.println(e.getStackTrace());
    }
}
