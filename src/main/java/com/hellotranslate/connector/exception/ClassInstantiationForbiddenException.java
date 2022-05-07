package com.hellotranslate.connector.exception;

public class ClassInstantiationForbiddenException extends InstantiationException {

    public ClassInstantiationForbiddenException(Class<?> thisClass)
    {
        super(String.format("Class <%s> cannot be instantiated", thisClass.getName()));
    }
}
