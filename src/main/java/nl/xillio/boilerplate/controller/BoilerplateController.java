package nl.xillio.boilerplate.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import nl.xillio.boilerplate.http.request.BoilerplateRequestDto;
import nl.xillio.boilerplate.http.request.BoilerplateRequestParser;
import nl.xillio.boilerplate.http.response.BoilerplateResponseDto;
import nl.xillio.boilerplate.http.response.BoilerplateResponseDtoFactory;
import nl.xillio.boilerplate.service.RequestBodyValidationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"/test"})// todo add endpoint name
@RequiredArgsConstructor
public class BoilerplateController {

    private final RequestBodyValidationService validator;
    private final BoilerplateResponseDtoFactory responseFactory;
    private final BoilerplateRequestParser requestParser;

    @PostMapping
    @ApiOperation(value = "")
    @ApiResponses({
            @ApiResponse(code = 200, message = ""), //todo rewrite
            @ApiResponse(code = 400, message = "") //todo clarify return code
    })
    public BoilerplateResponseDto doStuff(@RequestBody BoilerplateRequestDto requestDto)
    {
        return validator.isValid(requestDto)
               ? requestParser.executeRequest(requestDto)
               : responseFactory.getInvalidConfigurationResponse(requestDto.id());
    }
}
