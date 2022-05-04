package nl.hellotranslate.connector.jsonrpc;

import nl.hellotranslate.connector.exception.ClassInstantiationForbiddenException;

import java.util.List;

public final class Method {

    public static final String ENTITY_GET = "entity.get";
    public static final String ENTITY_GET_BINARY = "entity.get-binary";
    public static final String ENTITY_CREATE = "entity.create";

    public static final List<String> METHODS = List.of(
            ENTITY_GET,
            ENTITY_GET_BINARY,
            ENTITY_CREATE);

    private Method()
            throws ClassInstantiationForbiddenException
    {
        throw new ClassInstantiationForbiddenException(Method.class);
    }
}
