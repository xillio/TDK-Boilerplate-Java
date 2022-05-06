package com.hellotranslate.connector.jsonrpc.response;

import com.hellotranslate.connector.jsonrpc.response.components.NotMandatoryBoilerplateResponseClassMember;

public record ResponseDto(
        String id,
        String jsonrpc,
        NotMandatoryBoilerplateResponseClassMember object) {

}
