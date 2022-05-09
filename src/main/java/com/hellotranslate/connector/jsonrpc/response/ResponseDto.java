package com.hellotranslate.connector.jsonrpc.response;

import com.hellotranslate.connector.jsonrpc.response.components.ResponseClassMember;

import java.util.Map;

public record ResponseDto(
        String id,
        String jsonrpc,
        Map<String, Object> config,
        ResponseClassMember result) {

}
