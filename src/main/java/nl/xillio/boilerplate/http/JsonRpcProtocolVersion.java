package nl.xillio.boilerplate.http;

import nl.xillio.boilerplate.exception.ClassInstantiationForbiddenException;

public final class JsonRpcProtocolVersion {

    public static final String V2_0 = "2.0";

    private JsonRpcProtocolVersion()
            throws ClassInstantiationForbiddenException
    {
        throw new ClassInstantiationForbiddenException(JsonRpcProtocolVersion.class);
    }
}
