package com.hellotranslate.connector.jsonrpc.response.components;

import java.util.Optional;

public record Error(
        int code,
        String message,
        Optional<String> data)
        implements NotMandatoryBoilerplateResponseClassMember {

}
