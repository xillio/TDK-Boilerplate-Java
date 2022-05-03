package nl.xillio.boilerplate.http.response;

import nl.xillio.boilerplate.http.response.components.NotMandatoryBoilerplateResponseClassMember;

public record BoilerplateResponseDto(
        String id,
        String jsonrpc,
        NotMandatoryBoilerplateResponseClassMember object) {

}
