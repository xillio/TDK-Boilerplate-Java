package com.hellotranslate.connector.jsonrpc;

import com.hellotranslate.connector.exception.internal.ClassInstantiationForbiddenException;

public final class ProtocolVersion {

    public static final String V2_0 = "2.0";

    private ProtocolVersion()
            throws ClassInstantiationForbiddenException
    {
        throw new ClassInstantiationForbiddenException(ProtocolVersion.class);
    }
}
