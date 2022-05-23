package com.hellotranslate.connector.filesystemconnector;

import com.hellotranslate.connector.exception.internal.ClassInstantiationForbiddenException;
import com.hellotranslate.connector.exception.jsonrpc.response.ConnectorOperationFailedException;
import com.hellotranslate.connector.model.XDIP;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;

public class Utils {

    private Utils() throws ClassInstantiationForbiddenException {
        throw new ClassInstantiationForbiddenException(Utils.class);
    }

    public static BasicFileAttributes readAttributesOfAnEntity(Path entityPath) {
        try {
            return Files.getFileAttributeView(entityPath, BasicFileAttributeView.class).readAttributes();
        } catch (IOException e) {
            throw new ConnectorOperationFailedException("Couldn't read attributes of an entity, " + e.getMessage());
        }
    }

    public static String relativePathFromXdip(XDIP xdip) {
        String decodedPath = xdip.getDecodedPath();
        if (decodedPath.isEmpty())
            return "";

        return xdip.getDecodedPath().substring(1);
    }

    public static Path toPath(Configuration configuration, XDIP xdip) {
        String relativeXdipPath = relativePathFromXdip(xdip);
        return configuration.getBaseFolder().resolve(relativeXdipPath);
    }

    public static InputStream readFileContent(Path path) {
        try {
            return Files.newInputStream(path);
        } catch (IOException e) {
            throw new ConnectorOperationFailedException("Failed to read content of a file, " + e.getMessage());
        }
    }
}
