package com.hellotranslate.connector.repository.metadata;

import com.hellotranslate.connector.exception.jsonrpc.response.ConnectorOperationFailedException;
import com.hellotranslate.connector.exception.jsonrpc.response.MethodNotImplementedException;
import com.hellotranslate.connector.filesystemconnector.Configuration;
import com.hellotranslate.connector.model.DecoratorsContainer;
import com.hellotranslate.connector.model.XDIP;
import com.hellotranslate.connector.model.Entity;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.hellotranslate.connector.filesystemconnector.DecoratorsConverter.*;
import static com.hellotranslate.connector.filesystemconnector.Utils.*;
import static com.hellotranslate.connector.filesystemconnector.XdipProvider.buildXdip;
import static com.hellotranslate.connector.filesystemconnector.XdipProvider.getKind;

/**
 * This class is an implementation of the {@link MetadataRepository} interface.
 * <p>
 * To make TDK work, you must write an implementation how to get child entities and entity's metadata from your datastore.
 */
@Repository
public final class MetadataRepositoryImpl implements MetadataRepository {

    @Override
    public List<Entity> listChildren(XDIP xdip, Map<String, Object> config) {
        // TODO: re-implement

        Configuration configuration = new Configuration(config);
        var parentPath = toPath(configuration, xdip);
        BasicFileAttributes attributes = readAttributesOfAnEntity(parentPath);

        if (!attributes.isDirectory()) {
            return Collections.emptyList();
        }

        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(parentPath)) {

            List<Entity> result = new ArrayList<>();

            for (Path child : directoryStream) {
                result.add(buildChildEntity(configuration, xdip, child));
            }

            return result;
        } catch (IOException e) {
            throw new ConnectorOperationFailedException("Getting children failed, " + e.getMessage());
        }
    }

    @Override
    public List<XDIP> listReferences(XDIP xdip, Map<String, Object> config) {
        // TODO: re-implement

        return listChildren(xdip, config)
                .stream()
                .map(Entity::xdip)
                .collect(Collectors.toList());
    }

    @Override
    public Entity getEntityByXdip(XDIP xdip, Map<String, Object> config) {
        // TODO: re-implement

        Configuration configuration = new Configuration(config);
        Path entityPath = configuration
                .getBaseFolder()
                .resolve(
                        relativePathFromXdip(xdip)
                );
        BasicFileAttributes attributes = readAttributesOfAnEntity(entityPath);

        DecoratorsContainer decorators = convertToDecorators(configuration, xdip, entityPath, attributes);

        return new Entity(
                xdip,
                xdip,
                getKind(attributes),
                decorators,
                decorators
        );
    }

    private Entity buildChildEntity(Configuration configuration, XDIP parentXdip, Path childPath) {
        BasicFileAttributes attributes = readAttributesOfAnEntity(childPath);
        XDIP childXdip = buildXdip(configuration, parentXdip, childPath);
        DecoratorsContainer decorators = convertToDecorators(configuration, childXdip, childPath, attributes);

        return new Entity(
                childXdip,
                childXdip,
                getKind(attributes),
                decorators,
                decorators
        );
    }
}
