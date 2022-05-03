package nl.xillio.boilerplate.http.request.mapping;

import nl.xillio.boilerplate.exception.ClassInstantiationForbiddenException;

import java.util.Map;
import java.util.Set;

import static nl.xillio.boilerplate.http.JsonRpcMethod.*;
import static nl.xillio.boilerplate.http.request.fields.OptionalRequestBodyFields.*;

public final class MethodToOptionalFieldMapping {

    public static final Map<String, Set<String>> METHOD_TO_BODY = Map.of(
            ENTITY_GET,
            Set.of(CONFIG, XDIP, REQUEST_PARAMETERS),

            ENTITY_GET_BINARY,
            Set.of(CONFIG, XDIP),

            ENTITY_CREATE,
            Set.of(
                    CONFIG,
                    REQUEST_PARAMETERS,
                    ENTITY,
                    BINARY_CONTENTS)
    );

    private MethodToOptionalFieldMapping()
            throws ClassInstantiationForbiddenException
    {
        throw new ClassInstantiationForbiddenException(MethodToOptionalFieldMapping.class);
    }
}
