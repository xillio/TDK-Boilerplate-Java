package com.hellotranslate.connector.filesystemconnector;

import com.hellotranslate.connector.exception.internal.ClassInstantiationForbiddenException;
import com.hellotranslate.connector.exception.jsonrpc.response.ConnectorOperationFailedException;
import com.hellotranslate.connector.model.DecoratorsContainer;
import com.hellotranslate.connector.model.XDIP;
import com.hellotranslate.connector.model.decorators.*;

import java.net.URLConnection;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DecoratorsConverter {

    private static final String KIND_FILE = "File";
    private static final String KIND_FOLDER = "Folder";

    private DecoratorsConverter() throws ClassInstantiationForbiddenException {
        throw new ClassInstantiationForbiddenException(DecoratorsConverter.class);
    }

    public static DecoratorsContainer convertToDecorators(Configuration configuration, XDIP xdip, Path entityPath, BasicFileAttributes attributes) {
        return new DecoratorsContainer(
                convertToContainer(attributes),
                convertToContentType(attributes),
                convertToCreated(attributes),
                convertToFile(entityPath, attributes),
                convertToLanguage(attributes),
                convertToMimeType(entityPath, attributes),
                convertToModified(attributes),
                convertToName(entityPath),
                convertToParent(configuration, xdip, entityPath)
        );
    }

    public static String kindByAttributes(BasicFileAttributes attributes) {
        return attributes.isDirectory() ? KIND_FOLDER : KIND_FILE;
    }

    public static ContainerDecorator convertToContainer(BasicFileAttributes attributes) {
        if (attributes.isDirectory())
            return new ContainerDecorator(true);

        return null;
    }

    public static ContentTypeDecorator convertToContentType(BasicFileAttributes attributes) {
        String kind = kindByAttributes(attributes);
        return new ContentTypeDecorator(kind, kind);
    }

    public static CreatedDecorator convertToCreated(BasicFileAttributes attributes) {
        return new CreatedDecorator(
                ZonedDateTime.ofInstant(
                        attributes.creationTime().toInstant(),
                        ZoneId.of("Z")
                )
        );
    }

    public static FileDecorator convertToFile(Path entityPath, BasicFileAttributes attributes) {
        if (attributes.isDirectory())
            return null;

        String fileName = entityPath.getFileName().toString();
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);

        return new FileDecorator(
                extension,
                attributes.size()
        );
    }

    public static LanguageDecorator convertToLanguage(BasicFileAttributes attributes) {
        if (attributes.isDirectory())
            return null;

        return new LanguageDecorator(
                "en-US",
                null
        );
    }

    public static MimeTypeDecorator convertToMimeType(Path entityPath, BasicFileAttributes attributes) {
        if (attributes.isDirectory())
            return null;

        return new MimeTypeDecorator(
                URLConnection.guessContentTypeFromName(entityPath.getFileName().toString())
        );
    }

    public static ModifiedDecorator convertToModified(BasicFileAttributes attributes) {
        return new ModifiedDecorator(
                ZonedDateTime.ofInstant(
                        attributes.lastModifiedTime().toInstant(),
                        ZoneId.of("Z")
                )
        );
    }

    public static NameDecorator convertToName(Path entityPath) {
        return new NameDecorator(
                entityPath.getFileName().toString(),
                entityPath.getFileName().toString()
        );
    }

    public static ParentDecorator convertToParent(Configuration configuration, XDIP xdip, Path entityPath) {
        if (xdip.isRoot())
            return null;

        Path baseFolder = configuration.getBaseFolder();

        if (!entityPath.startsWith(baseFolder))
            throw new ConnectorOperationFailedException(
                    String.format("Connector error, entity oath %s mismatch the base folder %s", entityPath, baseFolder)
            );

        XDIP parentXdip = xdip.withDecodedPath("/");

        Path parent = baseFolder.relativize(entityPath).getParent();
        if (parent != null)
            parentXdip = parentXdip.withDecodedPath(parent.toString());

        return new ParentDecorator(parentXdip);
    }
}
