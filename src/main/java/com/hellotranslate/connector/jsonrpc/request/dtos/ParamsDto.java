package com.hellotranslate.connector.jsonrpc.request.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.hellotranslate.connector.model.XDIP;
import lombok.Getter;

import java.util.Map;

@Getter
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
}
