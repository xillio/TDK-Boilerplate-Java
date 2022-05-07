package com.hellotranslate.connector.jsonrpc.request.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.hellotranslate.connector.model.XDIP;
import lombok.Getter;

@Getter
public class ParamsDto {

    private final ConfigDto config;
    private XDIP xdip;
    private RequestParametersDto requestParameters;
    private EntityDto entity;
    private String binaryContents;

    @JsonCreator
    public ParamsDto(
            ConfigDto config,
            XDIP xdip,
            RequestParametersDto requestParameters) // Navigation constructor
    {
        this.config = config;
        this.xdip = xdip;
        this.requestParameters = requestParameters;
    }

    public ParamsDto(
            ConfigDto config,
            XDIP xdip) // Download constructor
    {
        this.config = config;
        this.xdip = xdip;
    }

    public ParamsDto(
            ConfigDto config,
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
