package nl.xillio.boilerplate.http.request.scope;

import nl.xillio.boilerplate.exception.ClassInstantiationForbiddenException;

public final class SupportedProjectScopes {

    public static final String PATH_CHILDREN_REFERENCE = "path_children_reference";
    public static final String PATH_CHILDREN_ENTITY = "path_children_entity";
    public static final String ENTITY = "entity";

    private SupportedProjectScopes()
            throws ClassInstantiationForbiddenException
    {
        throw new ClassInstantiationForbiddenException(SupportedProjectScopes.class);
    }
}
