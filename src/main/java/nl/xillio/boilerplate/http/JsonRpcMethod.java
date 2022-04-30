package nl.xillio.boilerplate.http;

import nl.xillio.boilerplate.exception.ClassInstantiationForbiddenException;

import java.util.List;

public final class JsonRpcMethod {

    public static final String ENTITY_GET = "entity.get";
    public static final String ENTITY_GET_BINARY = "entity.get-binary";
    public static final String ENTITY_CREATE = "entity.create";
    public static final String METADATA_DELIVER = "metadata.deliver";

    public static final List<String> METHODS = List.of(ENTITY_GET, ENTITY_GET_BINARY, ENTITY_CREATE, METADATA_DELIVER);

    private JsonRpcMethod()
            throws ClassInstantiationForbiddenException
    {
        throw new ClassInstantiationForbiddenException(JsonRpcMethod.class);
    }
}
