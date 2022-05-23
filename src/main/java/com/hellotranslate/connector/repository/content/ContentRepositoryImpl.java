package com.hellotranslate.connector.repository.content;

import com.hellotranslate.connector.exception.jsonrpc.response.MethodNotImplementedException;
import com.hellotranslate.connector.exception.jsonrpc.response.NoBinaryContentException;
import com.hellotranslate.connector.filesystemconnector.Configuration;
import com.hellotranslate.connector.model.XDIP;
import com.hellotranslate.connector.model.Entity;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Map;

import static com.hellotranslate.connector.filesystemconnector.Utils.*;

/**
 * This class is an implementation of the {@link ContentRepository} interface.
 * <p>
 * To make TDK work, you must write an implementation how to retrieve and
 * upload the content to the place where it is stored.
 */
@Repository
public final class ContentRepositoryImpl implements ContentRepository {

    @Override
    public InputStream downloadContent(XDIP xdip, Map<String, Object> config) {
        // TODO: implement

        var configuration = new Configuration(config);
        var path = toPath(configuration, xdip);

        BasicFileAttributes attributes = readAttributesOfAnEntity(path);

        if (!attributes.isRegularFile()) {
            throw new NoBinaryContentException("This is not a regular file");
        }
        
        return readFileContent(path);
    }

    @Override
    public Entity uploadContent(XDIP xdip,
                                Map<String, Object> config,
                                Entity entity,
                                InputStream binaryContents) {
        // TODO: implement
        throw new MethodNotImplementedException();
    }
}
