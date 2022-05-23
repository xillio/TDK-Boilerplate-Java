package com.hellotranslate.connector.repository.metadata;

import com.hellotranslate.connector.exception.jsonrpc.response.ConnectorOperationFailedException;
import com.hellotranslate.connector.exception.jsonrpc.response.MethodNotImplementedException;
import com.hellotranslate.connector.filesystemconnector.Configuration;
import com.hellotranslate.connector.model.XDIP;
import com.hellotranslate.connector.model.Entity;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.Map;

import static com.hellotranslate.connector.utils.DecoratorsConverter.*;

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
        throw new MethodNotImplementedException();
    }

    @Override
    public List<XDIP> listReferences(XDIP xdip, Map<String, Object> config) {
        // TODO: re-implement
        throw new MethodNotImplementedException();
    }

    @Override
    public Entity getEntityByXdip(XDIP xdip, Map<String, Object> config) {
        // TODO: re-implement

        Configuration configuration = new Configuration(config);
        String relativeXdipPath = relativePathFromXdip(xdip);

        Path entityPath = configuration.getBaseFolder().resolve(relativeXdipPath);
        BasicFileAttributes attributes = readAttributesOfAnEntity(entityPath);

        return new Entity(
                xdip,
                xdip,
                kindByAttributes(attributes),
                convertToDecorators(configuration, xdip, entityPath, attributes),
                convertToDecorators(configuration, xdip, entityPath, attributes)
        );
    }

    private BasicFileAttributes readAttributesOfAnEntity(Path entityPath) {
        try {
            return Files.getFileAttributeView(entityPath, BasicFileAttributeView.class).readAttributes();
        } catch (IOException e) {
            throw new ConnectorOperationFailedException("Couldn't read attributes of an entity, " + e.getMessage());
        }
    }

    private String relativePathFromXdip(XDIP xdip) {
        return xdip.getDecodedPath().substring(1);
    }
}
