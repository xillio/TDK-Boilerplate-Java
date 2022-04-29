package nl.xillio.boilerplate.http.response.components;

import java.util.Optional;

public record Error(
        int code,
        String message,
        Optional<String> data)
        implements NotMandatoryBoilerplateResponseClassMember {

}
