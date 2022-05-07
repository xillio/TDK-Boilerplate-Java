package com.hellotranslate.connector.exception;

import java.net.URI;

public class InvalidXdipException extends InstantiationException {

    public InvalidXdipException(String reason)
    {
        super(reason);
    }

    public InvalidXdipException(
            URI xdip,
            String reason)
    {
        super(String.format("%s - %s", reason, xdip));
    }
}
