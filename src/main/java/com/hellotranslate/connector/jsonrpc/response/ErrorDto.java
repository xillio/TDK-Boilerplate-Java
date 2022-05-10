package com.hellotranslate.connector.jsonrpc.response;

public class ErrorDto {

    private int code;
    private String message;
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
}
