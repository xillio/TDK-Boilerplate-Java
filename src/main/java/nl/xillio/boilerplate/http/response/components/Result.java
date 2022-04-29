package nl.xillio.boilerplate.http.response.components;

import java.util.Map;

public record Result(
        Map<String, Object> result)
        implements NotMandatoryBoilerplateResponseClassMember {

}
