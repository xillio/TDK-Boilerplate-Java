package nl.xillio.boilerplate.http.response;

import nl.xillio.boilerplate.http.JsonRpcProtocolVersion;
import nl.xillio.boilerplate.http.response.components.Error;
import nl.xillio.boilerplate.http.response.components.Result;
import nl.xillio.boilerplate.model.ResultBodyComponent;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Component
public class BoilerplateResponseDtoFactory {

    public BoilerplateResponseDto createErrorResponse(
            UUID contentId,
            int errorCode,
            String message,
            Optional<String> data)
    {
        return new BoilerplateResponseDto(
                contentId,
                JsonRpcProtocolVersion.V2_0,
                new Error(
                        errorCode,
                        message,
                        data
                )
        );
    }

    public BoilerplateResponseDto createSuccessResponse(
            UUID id,
            ResultBodyComponent result)
    {
        return new BoilerplateResponseDto(
                id,
                JsonRpcProtocolVersion.V2_0,
                new Result(
                        Map.of("result", result)
                )
        );
    }

    public BoilerplateResponseDto getInvalidConfigurationResponse(UUID id)
    {
        return new BoilerplateResponseDto(
                id,
                JsonRpcProtocolVersion.V2_0,
                new Error(
                        ErrorCodes.INVALID_CONFIGURATION,
                        "Invalid request body",
                        Optional.empty()
                )
        );
    }
}
