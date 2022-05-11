package com.hellotranslate.connector.jsonrpc.response.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorDto {

    private final int code;
    private final String message;
    private Object data;

    public ErrorDto(
            int code,
            String message)
    {
        this.code = code;
        this.message = message;
    }

    public ErrorDto(
            int code,
            String message,
            Object data)
    {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode()
    {
        return code;
    }

    public String getMessage()
    {
        return message;
    }

    public Object getData()
    {
        return data;
    }
}
