package nl.xillio.boilerplate.http.response;

import nl.xillio.boilerplate.http.response.components.NotMandatoryBoilerplateResponseClassMember;

import java.util.UUID;

public record BoilerplateResponseDto(
        String id,
        String jsonrpc,
        NotMandatoryBoilerplateResponseClassMember object) {

}
