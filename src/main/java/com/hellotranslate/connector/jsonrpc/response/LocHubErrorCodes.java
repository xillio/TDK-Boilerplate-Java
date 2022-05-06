package com.hellotranslate.connector.jsonrpc.response;

import com.hellotranslate.connector.exception.ClassInstantiationForbiddenException;

public final class LocHubErrorCodes {

    public static final int AUTHORIZATION_FAILED = 0;
    public static final int CONNECTOR_OPERATION_FAILED = 10;
    public static final int MISSING_DECORATOR = 70;
    public static final int NO_BINARY_CONTENT = 80;
    public static final int NO_SUCH_ENTITY = 90;
    public static final int NO_SUCH_SCOPE = 110;
    public static final int OPERATION_NOT_ALLOWED = 120;
    public static final int INVALID_CONFIGURATION = 150;

    private LocHubErrorCodes()
            throws ClassInstantiationForbiddenException
    {
        throw new ClassInstantiationForbiddenException(LocHubErrorCodes.class);
    }
}
