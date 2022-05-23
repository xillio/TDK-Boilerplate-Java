package com.hellotranslate.connector.service;

import com.hellotranslate.connector.filesystemconnector.Configuration;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ConfigValidationService {

    public void validate(Map<String, Object> config) {
        validateConfig(config);
        authorize(config);
    }

    private void validateConfig(Map<String, Object> config) {
        //TODO: implement config validation

        Configuration.validateConfiguration(config);

        // throw new InvalidConfigException("Invalid configuration");
    }

    private void authorize(Map<String, Object> config) {
        //TODO: implement authorization (optional)

        // throw new AuthorizationFailedException();
    }
}
