package com.hellotranslate.connector.exception.bodyvalidation;

import java.net.URI;

public final class InvalidXdipException extends RequestBodyValidationException {

    public InvalidXdipException(
            String reason,
            int errorCode)
    {
        super(reason, errorCode);
    }

    public InvalidXdipException(
            URI xdip,
            String reason,
            int errorCode)
    {
        super(String.format("%s - %s", reason, xdip), errorCode);
    }
}
