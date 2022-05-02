package nl.xillio.boilerplate.http.request.fields;

import nl.xillio.boilerplate.exception.ClassInstantiationForbiddenException;

public class OptionalRequestBodyFields {

    public static final String CONFIG = "config";
    public static final String REPOSITORY = "repository";
    public static final String XDIP = "xdip";
    public static final String REQUEST_PARAMETERS = "requestParameters";
    public static final String PROJECTION_SCOPES = "projectionScopes";
    public static final String ENTITY = "entity";


    private OptionalRequestBodyFields()
            throws ClassInstantiationForbiddenException
    {
        throw new ClassInstantiationForbiddenException(OptionalRequestBodyFields.class);
    }
}
