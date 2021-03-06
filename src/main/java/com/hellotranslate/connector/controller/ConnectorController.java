package com.hellotranslate.connector.controller;

import com.hellotranslate.connector.jsonrpc.request.Request;
import com.hellotranslate.connector.jsonrpc.request.RequestExecutor;
import com.hellotranslate.connector.jsonrpc.response.ResponseBody;
import com.hellotranslate.connector.service.RequestBodyValidationService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(
        value = {"/sample-connector"},
        produces= MediaType.APPLICATION_JSON_VALUE
)
public class ConnectorController {

    private final RequestBodyValidationService validator;
    private final RequestExecutor requestExecutor;

    public ConnectorController(RequestBodyValidationService validator, RequestExecutor requestExecutor) {
        this.validator = validator;
        this.requestExecutor = requestExecutor;
    }

    @PostMapping
    public ResponseBody handleJsonRpcRequest(@RequestBody Request requestDto, HttpServletRequest request) {
        validator.validate(requestDto);
        request.setAttribute("requestDto", requestDto);
        return requestExecutor.execute(requestDto);
    }
}

