package nl.hellotranslate.connector.jsonrpc.response;

import nl.hellotranslate.connector.jsonrpc.response.components.NotMandatoryBoilerplateResponseClassMember;

public record ResponseDto(
        String id,
        String jsonrpc,
        NotMandatoryBoilerplateResponseClassMember object) {

}
