package com.hellotranslate.connector.jsonrpc.request.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.hellotranslate.connector.model.XDIP;

import java.util.Map;

public class ParamsDto {

    private final Map<String, Object> config;
    private XDIP xdip;
    private RequestParametersDto requestParameters;
    private EntityDto entity;
    private String binaryContents;

    @JsonCreator
    public ParamsDto(
            Map<String, Object> config,
            XDIP xdip,
            RequestParametersDto requestParameters) // Navigation constructor
    {
        this.config = config;
        this.xdip = xdip;
        this.requestParameters = requestParameters;
    }

    public ParamsDto(
            Map<String, Object> config,
            XDIP xdip) // Download constructor
    {
        this.config = config;
        this.xdip = xdip;
    }

    public ParamsDto(
            Map<String, Object> config,
            RequestParametersDto requestParameters,
            EntityDto entity,
            String binaryContents) // Upload constructor
    {
        this.config = config;
        this.requestParameters = requestParameters;
        this.entity = entity;
        this.binaryContents = binaryContents;
    }

    public Map<String, Object> getConfig()
    {
        return config;
    }

    public XDIP getXdip()
    {
        return xdip;
    }

    public RequestParametersDto getRequestParameters()
    {
        return requestParameters;
    }

    public EntityDto getEntity()
    {
        return entity;
    }

    public String getBinaryContents()
    {
        return binaryContents;
    }
}
