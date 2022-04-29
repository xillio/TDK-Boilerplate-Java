package nl.xillio.boilerplate.http.response;

import nl.xillio.boilerplate.exception.ClassInstantiationForbiddenException;

public final class ErrorCodes {

    public static final int AUTHORIZATION_FAILED = 0;
    public static final int CONNECTOR_OPERATION_FAILED = 10;
    public static final int ENTITY_ALREADY_EXISTS = 20;
    public static final int HOST_NOT_REACHED = 30;
    public static final int INSUFFICIENT_STORAGE = 40;
    public static final int INVALID_QUERY = 50;
    public static final int MISCONFIGURED_SYSTEM = 60;
    public static final int MISSING_DECORATOR = 70;
    public static final int NO_BINARY_CONTENT = 80;
    public static final int NO_SUCH_ENTITY = 90;
    public static final int NO_SUCH_HANDLER = 100;
    public static final int NO_SUCH_SCOPE = 110;
    public static final int OPERATION_NOT_ALLOWED = 120;
    public static final int QUOTA_EXCEEDED = 130;
    public static final int UNSUPPORTED_CONTENT_TYPE = 140;
    public static final int INVALID_CONFIGURATION = 150;

    private ErrorCodes()
            throws ClassInstantiationForbiddenException
    {
        throw new ClassInstantiationForbiddenException(ErrorCodes.class);
    }
}
