package nl.xillio.boilerplate.http;

import nl.xillio.boilerplate.exception.ClassInstantiationForbiddenException;

public final class Version {

    public static final String V2_0 = "2.0";

    private Version()
            throws ClassInstantiationForbiddenException
    {
        throw new ClassInstantiationForbiddenException(Version.class);
    }
}
