package nl.xillio.boilerplate.http;

import nl.xillio.boilerplate.exception.ClassInstantiationForbiddenException;

import java.util.Arrays;
import java.util.List;

public final class HTTPMethod {

    public static final String GET = "entity.get";
    public static final String POST = "entity.post";

    private static final List<String> HTTP_METHODS = Arrays.asList(GET, POST);

    private HTTPMethod()
            throws ClassInstantiationForbiddenException
    {
        throw new ClassInstantiationForbiddenException(HTTPMethod.class);
    }

    public static List<String> getHttpMethods()
    {
        return HTTP_METHODS;
    }
}
