package com.hellotranslate.connector.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.hellotranslate.connector.exception.jsonrpc.bodyvalidation.InvalidXdipException;
import com.hellotranslate.connector.utils.XdipValidator;
import org.apache.http.client.utils.URIBuilder;
import org.apache.maven.shared.utils.StringUtils;

import javax.annotation.Nonnull;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

import static com.hellotranslate.connector.exception.lochub.LocHubErrors.CONNECTOR_OPERATION_FAILED;
import static org.apache.maven.shared.utils.StringUtils.split;

public class XDIP {

    public static final String SCHEME = "xdip";
    public static final String CONFIGURATION_ID_PATTERN = "[a-zA-Z0-9][a-zA-Z0-9-]{0,253}[a-zA-Z0-9]";

    public static final String QUERY_PARAM_LANGUAGE = "language";
    public static final String QUERY_PARAM_VERSION = "version";

    private static final Set<String> ALLOWED_QUERY_PARAMETERS = Set.of(QUERY_PARAM_LANGUAGE, QUERY_PARAM_VERSION);

    private final Map<String, String> encodedQueryParameters;
    private final URI uri;

    public XDIP(@Nonnull String encodedPath)
            throws InvalidXdipException
    {
        this("PLACEHOLDER", encodedPath);
    }

    public XDIP(
            @Nonnull String configurationId,
            @Nonnull String encodedPath)
            throws InvalidXdipException
    {
        this(
                new URIBuilder()
                        .setScheme("xdip")
                        .setHost(XdipValidator.notNullOrEmpty(
                                configurationId,
                                "configurationId"))
                        .setPath(decode(XdipValidator.startsWith(encodedPath, "/", "path")))
        );
    }

    public XDIP(URIBuilder uriBuilder)
            throws InvalidXdipException
    {
        this(build(uriBuilder));
    }

    XDIP(URI uri)
            throws InvalidXdipException
    {
        this.uri = uri;
        this.encodedQueryParameters = parseQueryParameters(uri.getRawQuery());

        schemeMustBeXdip();
        cannotUseFragment();
        cannotUseUserInfo();
        cannotUsePort();
        configurationIdMustMatchPattern();
        canOnlyUseWhitelistedQueryParameters();
    }

    @JsonCreator
    private static XDIP buildXdip(String uri)
            throws InvalidXdipException
    {
        return new XDIP(URI.create(uri));
    }

    private static String decode(String part)
    {
        return URLDecoder.decode(part, StandardCharsets.UTF_8);
    }

    private static URI build(URIBuilder builder)
    {
        try {
            // Remove unwanted data
            builder.setPort(-1);
            builder.setFragment(null);
            builder.setUserInfo(null);

            // Clear empty query lists
            if (builder.getQueryParams().isEmpty()) {
                builder.removeQuery();
            }

            // Remove duplicated slashes in path
            String cleanPath = Optional.ofNullable(builder.getPath()).orElse("");
            while (cleanPath.contains("//")) {
                cleanPath = StringUtils.replace(cleanPath, "//", "/");
            }
            builder.setPath(cleanPath);

            // Build the uri
            String uri = builder.build().toString();

            // Replace character + with %20 in query and fragment parts
            if (uri.lastIndexOf('?') > 0) {
                uri = uri.substring(0, uri.lastIndexOf('?')) +
                        StringUtils.replace(uri.substring(uri.lastIndexOf('?')), "+", "%20");
            }
            // Replace character + with %2D in path part (it affects path part only because query and fragment parts were already encoded)
            uri = StringUtils.replace(uri, "+", "%2B");

            return URI.create(uri);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(
                    "Could not parse a new URI from the given values.",
                    e);
        }
    }

    private Map<String, String> parseQueryParameters(String rawQuery)
    {
        if (rawQuery == null) {
            return Collections.emptyMap();
        }

        return Arrays.stream(split(rawQuery, "&"))
                .map(query -> split(query, "="))
                .collect(Collectors.toMap(
                        parts -> parts[0],
                        parts -> (parts.length > 1) ? parts[1] : ""));
    }

    private void schemeMustBeXdip()
            throws InvalidXdipException
    {
        if (!SCHEME.equals(uri.getScheme())) {
            throw new InvalidXdipException("uri", "XDIP URLs must use the " + SCHEME + " scheme.", CONNECTOR_OPERATION_FAILED.code());
        }
    }

    private void cannotUseFragment()
            throws InvalidXdipException
    {
        if (uri.getRawFragment() != null) {
            throw new InvalidXdipException("uri", "XDIP URLs cannot use fragment parameters", CONNECTOR_OPERATION_FAILED.code());
        }
    }

    private void cannotUseUserInfo()
            throws InvalidXdipException
    {
        if (uri.getRawUserInfo() != null) {
            throw new InvalidXdipException("uri", "XDIP URLs cannot use user information", CONNECTOR_OPERATION_FAILED.code());
        }
    }

    private void cannotUsePort()
            throws InvalidXdipException
    {
        if (uri.getPort() != -1) {
            throw new InvalidXdipException("uri", "XDIP URLs cannot use port specifications", CONNECTOR_OPERATION_FAILED.code());
        }
    }

    private void configurationIdMustMatchPattern()
            throws InvalidXdipException
    {
        validateConfigurationId(uri.getHost());
    }

    private void validateConfigurationId(String configurationId)
            throws InvalidXdipException
    {
        if (configurationId == null || !configurationId.matches(XDIP.CONFIGURATION_ID_PATTERN)) {
            throw new InvalidXdipException(
                    "Configuration ids can only contain alphanumerics and dashes, but the id cannot " +
                    "start or end with a dash. The minimum length is 2 and maximum is 255.", CONNECTOR_OPERATION_FAILED.code());
        }
    }


    private void canOnlyUseWhitelistedQueryParameters()
            throws InvalidXdipException
    {
        if (encodedQueryParameters.size() > 0
                && (encodedQueryParameters.containsValue(null)
                || encodedQueryParameters.containsValue(""))) {
            throw new InvalidXdipException(
                    uri,
                    "XDIP URLs can not contain flags as query parameters", CONNECTOR_OPERATION_FAILED.code());
        }
        if (hasDisallowedQueries()) {
            throw new InvalidXdipException(
                    uri,
                    "XDIP URL contains invalid query parameters. Valid parameters are [\"" + String.join(
                            "\", \"",
                            ALLOWED_QUERY_PARAMETERS) + "\"]", CONNECTOR_OPERATION_FAILED.code());
        }
    }

    private boolean hasDisallowedQueries() {
        return encodedQueryParameters.keySet()
                .stream()
                .anyMatch(key -> !ALLOWED_QUERY_PARAMETERS.contains(key));
    }
}
