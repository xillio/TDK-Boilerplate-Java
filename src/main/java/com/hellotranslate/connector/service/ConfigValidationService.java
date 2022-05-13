package com.hellotranslate.connector.service;

import com.hellotranslate.connector.exception.jsonrpc.bodyvalidation.InvalidConfigException;
import com.hellotranslate.connector.exception.jsonrpc.response.AuthorizationFailedException;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.hellotranslate.connector.exception.lochub.LocHubErrors.AUTHORIZATION_FAILED;
import static com.hellotranslate.connector.exception.lochub.LocHubErrors.INVALID_CONFIGURATION;

@Service
public class ConfigValidationService {

    public void validate(String requestId, Map<String, Object> config) throws InvalidConfigException {

        if (configIsInvalid(config)) {
            throw new InvalidConfigException(requestId, INVALID_CONFIGURATION.message(), INVALID_CONFIGURATION.code());
        }

        if (failedToAuthorize(config)) {
            throw new AuthorizationFailedException(requestId, AUTHORIZATION_FAILED.message(), AUTHORIZATION_FAILED.code());
        }
    }

    private boolean configIsInvalid(Map<String, Object> config) {
        //TODO: implement config validation (optional)
        return false;
    }

    private boolean failedToAuthorize(Map<String, Object> config) {
        //TODO: implement authorization (optional)
        return false;
    }
}
