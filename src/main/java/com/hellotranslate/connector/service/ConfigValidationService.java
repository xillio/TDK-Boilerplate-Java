package com.hellotranslate.connector.service;

import com.hellotranslate.connector.exception.bodyvalidation.InvalidConfigException;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.hellotranslate.connector.jsonrpc.response.LocHubErrorCodes.INVALID_CONFIGURATION;

@Service
public class ConfigValidationService {

    public void validate(Map<String, Object> config)
            throws InvalidConfigException
    {
        if(configIsInvalid(config)){
            throw new InvalidConfigException("Config is invalid", INVALID_CONFIGURATION);
        }
    }

    private boolean configIsInvalid(Map<String, Object> config)
    {
        //todo: implement config validation and authentication
        return false;
    }
}
