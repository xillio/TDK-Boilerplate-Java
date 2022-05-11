package com.hellotranslate.connector.service;

import com.hellotranslate.connector.exception.jsonrpc.bodyvalidation.InvalidConfigException;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.hellotranslate.connector.exception.lochub.LocHubErrors.INVALID_CONFIGURATION;

@Service
public class ConfigValidationService {

    public void validate(String requestId, Map<String, Object> config)
            throws InvalidConfigException {
        if (configIsInvalid(config)) {
            throw new InvalidConfigException(requestId, "Config is invalid", INVALID_CONFIGURATION.code());
        }
    }

    private boolean configIsInvalid(Map<String, Object> config) {
        //todo: implement config validation and authentication
        return false;
    }
}
