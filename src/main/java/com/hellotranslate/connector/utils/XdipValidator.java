package com.hellotranslate.connector.utils;

import com.hellotranslate.connector.exception.internal.ClassInstantiationForbiddenException;

public final class XdipValidator {

    private XdipValidator()
            throws ClassInstantiationForbiddenException
    {
        throw new ClassInstantiationForbiddenException(XdipValidator.class);
    }

    public static <T> void notNull(
            T value,
            String parameter)
    {
        if (value == null) {
            throw error(String.format("'%s' cannot be null.", parameter));
        }
    }

    public static String notNullOrEmpty(
            String value,
            String parameter)
    {
        notNull(value, parameter);
        if (value.isEmpty()) {
            throw error(String.format("'%s' cannot be empty.", parameter));
        }
        return value;
    }

    public static String startsWith(
            String value,
            String prefix,
            String parameter)
    {
        notNull(value, parameter);
        if (!value.startsWith(prefix)) {
            throw error(String.format("'%s' must start with '%s'.", parameter, prefix), value);
        }
        return value;
    }

    private static IllegalArgumentException error(String message)
    {
        return error(message, null);
    }

    private static IllegalArgumentException error(
            String message,
            String input)
    {
        if (input != null) {
            message += "\nProvided input: " + input;
        }
        return new IllegalArgumentException(message);
    }
}
