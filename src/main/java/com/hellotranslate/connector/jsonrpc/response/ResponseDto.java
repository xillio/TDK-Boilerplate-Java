package com.hellotranslate.connector.jsonrpc.response;

import com.hellotranslate.connector.jsonrpc.request.dtos.ConfigDto;
import com.hellotranslate.connector.jsonrpc.response.components.NotMandatoryBoilerplateResponseClassMember;

public record ResponseDto(
        String id,
        String jsonrpc,
        ConfigDto config,
        NotMandatoryBoilerplateResponseClassMember object) {

}
