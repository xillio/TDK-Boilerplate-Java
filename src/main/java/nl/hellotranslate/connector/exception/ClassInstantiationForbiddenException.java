package nl.hellotranslate.connector.exception;

public class ClassInstantiationForbiddenException extends IllegalAccessException {

    public ClassInstantiationForbiddenException(Class<?> thisClass)
    {
        super(String.format("Class <%s> cannot be instantiated", thisClass.getName()));
    }
}
