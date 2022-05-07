package com.hellotranslate.connector.controller;

import com.hellotranslate.connector.jsonrpc.request.RequestDto;
import com.hellotranslate.connector.jsonrpc.request.RequestExecutor;
import com.hellotranslate.connector.jsonrpc.response.ResponseDto;
import com.hellotranslate.connector.jsonrpc.response.ResponseDtoFactory;
import com.hellotranslate.connector.service.RequestBodyValidationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"/sample-connector"})
@RequiredArgsConstructor
public class BoilerplateController {

    private final RequestBodyValidationService validator;
    private final ResponseDtoFactory responseFactory;
    private final RequestExecutor requestExecutor;

    @PostMapping
    @ApiOperation(value = "")
    @ApiResponses({
            @ApiResponse(code = 200, message = ""), //todo clarify message
    })
    public ResponseDto handleJsonRpcRequest(
            @RequestBody RequestDto requestDto)
    {
        return validator.validate(requestDto)
               ? requestExecutor.execute(requestDto)
               : responseFactory.createInvalidConfigurationResponse(requestDto.id(),
                                                                    requestDto.params()
                                                                              .getConfig());
    }
}
