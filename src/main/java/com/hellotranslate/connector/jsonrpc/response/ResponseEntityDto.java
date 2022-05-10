package com.hellotranslate.connector.jsonrpc.response;

import com.hellotranslate.connector.model.decorators.OriginalResponseDto;

public record ResponseEntityDto(String id, String kind, OriginalResponseDto original) {
}
