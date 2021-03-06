package com.hellotranslate.connector.jsonrpc.response.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Error {

    private final int code;
    private final String message;
    private Object data;

    public Error(
            int code,
            String message) {
        this.code = code;
        this.message = message;
    }

    public Error(
            int code,
            String message,
            Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }
}
