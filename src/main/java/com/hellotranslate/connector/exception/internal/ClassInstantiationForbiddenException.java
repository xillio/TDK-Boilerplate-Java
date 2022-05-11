package com.hellotranslate.connector.exception.internal;

public final class ClassInstantiationForbiddenException extends InstantiationException {

    public ClassInstantiationForbiddenException(Class<?> thisClass)
    {
        super(String.format("Class <%s> cannot be instantiated", thisClass.getName()));
    }
}
