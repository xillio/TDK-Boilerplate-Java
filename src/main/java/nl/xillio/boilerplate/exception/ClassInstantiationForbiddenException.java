package nl.xillio.boilerplate.exception;

public class ClassInstantiationForbiddenException extends IllegalAccessException {

    public ClassInstantiationForbiddenException(Class<?> thisClass)
    {
        super(String.format("Class <%s> cannot be instantiated", thisClass.getName()));
    }
}
