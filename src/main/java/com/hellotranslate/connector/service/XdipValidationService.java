package com.hellotranslate.connector.service;

import com.hellotranslate.connector.exception.jsonrpc.bodyvalidation.InvalidXdipException;
import com.hellotranslate.connector.jsonrpc.request.RequestDto;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.hellotranslate.connector.exception.lochub.LocHubErrors.CONNECTOR_OPERATION_FAILED;
import static org.apache.maven.shared.utils.StringUtils.split;

@Service
public class XdipValidationService {

    private static final String CONFIGURATION_ID_PATTERN = "[a-zA-Z0-9][a-zA-Z0-9-]{0,253}[a-zA-Z0-9]";
    private static final String QUERY_PARAM_LANGUAGE = "language";
    private static final String QUERY_PARAM_VERSION = "version";
    private static final String SCHEME = "xdip";
    private static final Set<String> ALLOWED_QUERY_PARAMETERS = Set.of(QUERY_PARAM_LANGUAGE, QUERY_PARAM_VERSION);

    private URI uri;
    private Map<String, String> encodedQueryParameters;

    public void validate(RequestDto requestDto) {
        uri = URI.create(requestDto.params().xdip());
        encodedQueryParameters = parseQueryParameters(uri.getRawQuery());

        schemeMustBeXdip(requestDto);
        cannotUseFragment(requestDto);
        cannotUseUserInfo(requestDto);
        cannotUsePort(requestDto);
        configurationIdMustMatchPattern(requestDto);
        canOnlyUseWhitelistedQueryParameters(requestDto);
    }


    private Map<String, String> parseQueryParameters(String rawQuery) {
        if (rawQuery == null) {
            return Collections.emptyMap();
        }

        return Arrays.stream(split(rawQuery, "&"))
                .map(query -> split(query, "="))
                .collect(Collectors.toMap(
                        parts -> parts[0],
                        parts -> (parts.length > 1) ? parts[1] : ""));
    }

    private void schemeMustBeXdip(RequestDto requestDto)
            throws InvalidXdipException {
        if (!SCHEME.equals(uri.getScheme())) {
            throw new InvalidXdipException(
                    requestDto.id(),
                    String.format("%s - XDIP URLs must use the " + SCHEME + " scheme", uri),
                    CONNECTOR_OPERATION_FAILED.code());
        }
    }

    private void cannotUseFragment(RequestDto requestDto)
            throws InvalidXdipException {
        if (uri.getRawFragment() != null) {
            throw new InvalidXdipException(
                    requestDto.id(),
                    String.format("%s - XDIP URLs cannot use fragment parameters", uri),
                    CONNECTOR_OPERATION_FAILED.code());
        }
    }

    private void cannotUseUserInfo(RequestDto requestDto)
            throws InvalidXdipException {
        if (uri.getRawUserInfo() != null) {
            throw new InvalidXdipException(
                    requestDto.id(),
                    String.format("%s - XDIP URLs cannot use user information", uri),
                    CONNECTOR_OPERATION_FAILED.code());
        }
    }

    private void cannotUsePort(RequestDto requestDto)
            throws InvalidXdipException {
        if (uri.getPort() != -1) {
            throw new InvalidXdipException(
                    requestDto.id(),
                    String.format("%s - XDIP URLs cannot use port specifications", uri),
                    CONNECTOR_OPERATION_FAILED.code());
        }
    }

    private void configurationIdMustMatchPattern(RequestDto requestDto)
            throws InvalidXdipException {
        validateConfigurationId(requestDto.id(), uri.getHost());
    }

    private void validateConfigurationId(String id, String configurationId)
            throws InvalidXdipException {
        if (configurationId == null || !configurationId.matches(CONFIGURATION_ID_PATTERN)) {
            throw new InvalidXdipException(id,
                    "Configuration ids can only contain alphanumerics and dashes, but the id cannot " +
                            "start or end with a dash. The minimum length is 2 and maximum is 255.", CONNECTOR_OPERATION_FAILED.code());
        }
    }


    private void canOnlyUseWhitelistedQueryParameters(RequestDto requestDto)
            throws InvalidXdipException {
        if (encodedQueryParameters.size() > 0
                && (encodedQueryParameters.containsValue(null)
                || encodedQueryParameters.containsValue(""))) {
            throw new InvalidXdipException(
                    requestDto.id(),
                    String.format("%s - XDIP URLs can not contain flags as query parameters", uri),
                    CONNECTOR_OPERATION_FAILED.code());
        }
        if (hasDisallowedQueries()) {
            throw new InvalidXdipException(
                    requestDto.id(),
                    String.format("%s - XDIP URL contains invalid query parameters. Valid parameters are [\"" + String.join(
                            "\", \"",
                            ALLOWED_QUERY_PARAMETERS) + "\"]", uri),
                    CONNECTOR_OPERATION_FAILED.code());
        }
    }

    private boolean hasDisallowedQueries() {
        return encodedQueryParameters.keySet()
                .stream()
                .anyMatch(key -> !ALLOWED_QUERY_PARAMETERS.contains(key));
    }
}
