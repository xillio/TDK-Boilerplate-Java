package nl.hellotranslate.connector.jsonrpc;

import nl.hellotranslate.connector.exception.ClassInstantiationForbiddenException;

public final class ProtocolVersion {

    public static final String V2_0 = "2.0";

    private ProtocolVersion()
            throws ClassInstantiationForbiddenException
    {
        throw new ClassInstantiationForbiddenException(ProtocolVersion.class);
    }
}
