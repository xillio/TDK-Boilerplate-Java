package nl.xillio.boilerplate.model.datatypes;

import nl.xillio.boilerplate.model.decorators.*;

public record Original(
        Container container,
        ContentType contentType,
        Created created,
        Modified modified,
        Name name,
        Parent parent) {

}
