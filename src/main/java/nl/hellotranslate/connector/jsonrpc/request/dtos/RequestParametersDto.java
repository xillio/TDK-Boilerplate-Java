package nl.hellotranslate.connector.jsonrpc.request.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public class RequestParametersDto {

    private final String[] projectionScopes;
    private String[] projectionIncludes;
    private String[] projectionExcludes;
    private int offset;
    private int limit;

    @JsonCreator
    public RequestParametersDto(
            String[] projectionScopes,
            String[] projectionIncludes,
            String[] projectionExcludes,
            int offset,
            int limit) // Request parameters for navigation
    {
        this.projectionScopes = projectionScopes;
        this.projectionIncludes = projectionIncludes;
        this.projectionExcludes = projectionExcludes;
        this.offset = offset;
        this.limit = limit;
    }

    public RequestParametersDto(String[] projectionScopes) // Request parameters for uploading
    {
        this.projectionScopes = projectionScopes;
    }
}
