package nl.xillio.boilerplate.http.request.fields;

import nl.xillio.boilerplate.exception.ClassInstantiationForbiddenException;

public final class MandatoryRequestBodyFields {

    public static final String JSONRPC = "jsonrpc";
    public static final String ID = "id";
    public static final String METHOD = "method";
    public static final String PARAMS = "params";

    private MandatoryRequestBodyFields()
            throws ClassInstantiationForbiddenException
    {
        throw new ClassInstantiationForbiddenException(MandatoryRequestBodyFields.class);
    }
}
