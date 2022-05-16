package com.hellotranslate.connector.jsonrpc.request.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;

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

    public String[] getProjectionScopes() {
        return projectionScopes;
    }

    public String[] getProjectionIncludes() {
        return projectionIncludes;
    }

    public String[] getProjectionExcludes() {
        return projectionExcludes;
    }

    public int getOffset() {
        return offset;
    }

    public int getLimit() {
        return limit;
    }
}
