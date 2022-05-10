package com.hellotranslate.connector.jsonrpc.request.entity;

import com.hellotranslate.connector.exception.ClassInstantiationForbiddenException;

import java.util.List;

public final class SupportedKinds {

    private static final String FILE = "File";
    private static final String FOLDER = "Folder";

    public static final List<String> SUPPORTED_KINDS = List.of(FILE, FOLDER);

    private SupportedKinds()
            throws ClassInstantiationForbiddenException
    {
        throw new ClassInstantiationForbiddenException(SupportedKinds.class);
    }
}
