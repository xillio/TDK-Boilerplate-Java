package com.hellotranslate.connector.exception;

public class MethodNotImplementedException extends IllegalStateException {

    private static final String DEFAULT_MESSAGE = "Method not implemented";

    public MethodNotImplementedException()
    {
        super(DEFAULT_MESSAGE);
    }

    public MethodNotImplementedException(Exception cause)
    {
        super(String.format("%s was not implemented", cause.getStackTrace()[0].getMethodName()));
    }

    public MethodNotImplementedException(String message)
    {
        super(message);
    }
}
