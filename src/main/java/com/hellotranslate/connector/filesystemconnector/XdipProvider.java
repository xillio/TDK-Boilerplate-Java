package com.hellotranslate.connector.filesystemconnector;

import com.hellotranslate.connector.exception.internal.ClassInstantiationForbiddenException;
import com.hellotranslate.connector.exception.jsonrpc.response.ConnectorOperationFailedException;
import com.hellotranslate.connector.model.XDIP;

import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

public class XdipProvider {

    private static final String KIND_FILE = "File";
    private static final String KIND_FOLDER = "Folder";

    private XdipProvider() throws ClassInstantiationForbiddenException {
        throw new ClassInstantiationForbiddenException(XdipProvider.class);
    }

    public static XDIP buildXdip(Configuration configuration, XDIP xdip, Path entityPath) {
        Path baseFolder = configuration.getBaseFolder();

        if (!entityPath.startsWith(baseFolder)) {
            throw new ConnectorOperationFailedException(
                    String.format("Connector error, entity oath %s mismatch the base folder %s", entityPath, baseFolder)
            );
        }

        Path relativePath = baseFolder.relativize(entityPath);
        return xdip.withDecodedPath(relativePath.toString());
    }

    public static String getKind(BasicFileAttributes attributes) {
        if (attributes.isRegularFile()) {
            return KIND_FILE;
        }
        if (attributes.isDirectory()) {
            return KIND_FOLDER;
        }
        return null;
    }
}
