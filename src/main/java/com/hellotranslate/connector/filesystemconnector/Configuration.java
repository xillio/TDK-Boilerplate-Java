package com.hellotranslate.connector.filesystemconnector;

import com.hellotranslate.connector.exception.jsonrpc.bodyvalidation.InvalidConfigException;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Configuration {

    private static final String CONFIG_KEY_FOLDER = "folder";

    public static void validateConfiguration(Map<String, Object> config) {
        if (!config.containsKey(CONFIG_KEY_FOLDER))
            throw new InvalidConfigException("Configuration is missing mandatory value 'folder'.");

        Object valueFolder = config.get(CONFIG_KEY_FOLDER);
        if (!(valueFolder instanceof String))
            throw new InvalidConfigException("Configuration value 'folder' must be a String.");
    }

    private final Path baseFolder;

    public Configuration(Map<String, Object> config) {
        String folder = (String) config.get(CONFIG_KEY_FOLDER);
        this.baseFolder = Paths.get(folder);
    }

    public Path getBaseFolder() {
        return baseFolder;
    }
}
