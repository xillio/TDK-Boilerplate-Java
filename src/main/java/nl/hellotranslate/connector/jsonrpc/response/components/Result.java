package nl.hellotranslate.connector.jsonrpc.response.components;

import java.util.Map;

public record Result(
        Map<String, Object> result)
        implements NotMandatoryBoilerplateResponseClassMember {

}
