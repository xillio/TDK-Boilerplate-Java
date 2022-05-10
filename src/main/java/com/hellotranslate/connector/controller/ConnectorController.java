package com.hellotranslate.connector.controller;

import com.hellotranslate.connector.jsonrpc.request.RequestDto;
import com.hellotranslate.connector.jsonrpc.request.RequestExecutor;
import com.hellotranslate.connector.jsonrpc.response.ResponseBody;
import com.hellotranslate.connector.jsonrpc.response.ResponseDtoFactory;
import com.hellotranslate.connector.service.RequestBodyValidationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"/sample-connector"})
public class ConnectorController {

    private final RequestBodyValidationService validator;
    private final ResponseDtoFactory responseFactory;
    private final RequestExecutor requestExecutor;

    public ConnectorController(
            RequestBodyValidationService validator,
            ResponseDtoFactory responseFactory,
            RequestExecutor requestExecutor)
    {
        this.validator = validator;
        this.responseFactory = responseFactory;
        this.requestExecutor = requestExecutor;
    }

    @PostMapping
    public ResponseBody handleJsonRpcRequest(
            @RequestBody RequestDto requestDto)
    {
        return validator.validate(requestDto)
               ? requestExecutor.execute(requestDto)
               : responseFactory.createInvalidConfigurationResponse(requestDto.id());
    }
}
