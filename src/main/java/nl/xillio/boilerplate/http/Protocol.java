package nl.xillio.boilerplate.http;

import nl.xillio.boilerplate.exception.ClassInstantiationForbiddenException;

public final class Protocol { // todo probably delete

    public static final String JSONRPC = "jsonrpc";

    private Protocol()
            throws ClassInstantiationForbiddenException
    {
        throw new ClassInstantiationForbiddenException(Protocol.class);
    }
}
