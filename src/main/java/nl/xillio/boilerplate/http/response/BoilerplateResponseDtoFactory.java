package nl.xillio.boilerplate.http.response;

import nl.xillio.boilerplate.http.Version;
import nl.xillio.boilerplate.http.response.components.Error;
import nl.xillio.boilerplate.http.response.components.Result;
import org.springframework.stereotype.Component;

import java.util.HashMap;
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
                Version.V2_0,
                new Error(
                        errorCode,
                        message,
                        data
                )
        );
    }

    public BoilerplateResponseDto createSuccessResponse(UUID contentId)
    {
        return new BoilerplateResponseDto(
                contentId,
                Version.V2_0,
                new Result(
                        new HashMap<>() //todo fix
                )
        );
    }
}
