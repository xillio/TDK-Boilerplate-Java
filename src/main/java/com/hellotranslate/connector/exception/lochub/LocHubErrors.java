package com.hellotranslate.connector.exception.lochub;

import com.hellotranslate.connector.exception.internal.ClassInstantiationForbiddenException;

public final class LocHubErrors {

    public static final LocHubError AUTHORIZATION_FAILED = new LocHubError(0, "Authorization Failed");
    public static final LocHubError CONNECTOR_OPERATION_FAILED = new LocHubError(10, "Connector Operation Failed");
    public static final LocHubError MISSING_DECORATOR = new LocHubError(70, "Missing Decorator");
    public static final LocHubError NO_BINARY_CONTENT = new LocHubError(80, "No Binary Content");
    public static final LocHubError NO_SUCH_ENTITY = new LocHubError(90, "No Such Entity");
    public static final LocHubError NO_SUCH_SCOPE = new LocHubError(110, "No Such Scope");
    public static final LocHubError OPERATION_NOT_ALLOWED = new LocHubError(120, "Operation Not Allowed");
    public static final LocHubError INVALID_CONFIGURATION = new LocHubError(150, "Invalid Configuration");

    private LocHubErrors() throws ClassInstantiationForbiddenException {
        throw new ClassInstantiationForbiddenException(LocHubErrors.class);
    }
}
