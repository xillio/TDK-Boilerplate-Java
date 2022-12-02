package com.hellotranslate.connector.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.hellotranslate.connector.exception.jsonrpc.bodyvalidation.InvalidXdipException;
import org.apache.http.client.utils.URIBuilder;
import org.apache.maven.shared.utils.StringUtils;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

import static org.apache.maven.shared.utils.StringUtils.split;

public class XDIP {

    private static final String SCHEME = "xdip";
    private static final String CONFIGURATION_ID_PATTERN = "[a-zA-Z0-9][a-zA-Z0-9-]{0,253}[a-zA-Z0-9]";

    private final URI uri;

    public XDIP(URIBuilder uriBuilder) {
        this(build(uriBuilder));
    }

    XDIP(URI uri) {
        this.uri = uri;

        schemeMustBeXdip();
        cannotUseFragment();
        cannotUseUserInfo();
        cannotUsePort();
        configurationIdMustMatchPattern();
    }

    @JsonCreator
    private static XDIP buildXdip(String uri) {
        return new XDIP(URI.create(uri));
    }

    private static String decode(String part) {
        return URLDecoder.decode(part, StandardCharsets.UTF_8);
    }

    private static URI build(URIBuilder builder) {
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
            throw new InvalidXdipException("Could not parse a new URI from the given values.");
        }
    }

    public String getEncodedPath() {
        return uri.getRawPath();
    }

    public String[] getEncodedPathParts() {
        return split(getEncodedPath(), "/");
    }

    public String getDecodedPath() {
        return uri.getPath();
    }

    public String[] getDecodedPathParts() {
        return split(getDecodedPath(), "/");
    }

    public boolean isRoot() {
        return "/".equals(getDecodedPath());
    }

    public String getConfigurationId() {
        return uri.getHost();
    }

    /**
     * Construct a new XDIP URL by parsing the given string with the current configuration id
     *
     * @param encodedPath the URL encoded path
     * @return the resulting XDIP URL
     */
    public XDIP withPath(String encodedPath) {
        return new XDIP(new URIBuilder(uri).setPath(decode(encodedPath)));
    }

    /**
     * Construct a new XDIP URL by parsing the given string with the current configuration id
     *
     * @param decodedPath the URL path
     * @return the resulting XDIP URL
     */
    public XDIP withDecodedPath(String decodedPath) {
        return new XDIP(new URIBuilder(uri).setPath(decodedPath));
    }

    /**
     * Construct a new XDIP URL from the current XDIP with the given configuration id
     *
     * @param configurationId the configuration id
     * @return the resulting XDIP URL
     */
    public XDIP withConfigurationId(String configurationId) {
        return new XDIP(new URIBuilder(uri).setHost(configurationId));
    }

    /**
     * Constructs a new XDIP URL by parsing the given string and then resolving it against the current XDIP URL.
     *
     * @param encodedPath the URL encoded path
     * @return the resulting XDIP URL
     */
    public XDIP resolve(String encodedPath) {
        return new XDIP(this.uri.resolve(encodedPath));
    }

    @JsonValue
    @Override
    public String toString() {
        return uri.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (o.getClass() == getClass()) {
            return ((XDIP) o).uri.equals(uri);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return uri.hashCode();
    }

    private Map<String, String> parseQueryParameters(String rawQuery) {
        if (rawQuery == null) {
            return Collections.emptyMap();
        }

        return Arrays.stream(split(rawQuery, "&"))
                .map(query -> split(query, "="))
                .collect(Collectors.toMap(parts -> parts[0], parts -> (parts.length > 1) ? parts[1] : ""));
    }

    private void schemeMustBeXdip() {
        if (!SCHEME.equals(uri.getScheme())) {
            throw new InvalidXdipException(String.format("%s - XDIP URLs must use the %s scheme.", uri, SCHEME));
        }
    }

    private void cannotUseFragment() {
        if (uri.getRawFragment() != null) {
            throw new InvalidXdipException(String.format("%s - XDIP URLs cannot use fragment parameters", uri));
        }
    }

    private void cannotUseUserInfo() {
        if (uri.getRawUserInfo() != null) {
            throw new InvalidXdipException(String.format("%s - XDIP URLs cannot use user information", uri));
        }
    }

    private void cannotUsePort() {
        if (uri.getPort() != -1) {
            throw new InvalidXdipException(String.format("%s - XDIP URLs cannot use port specifications", uri));
        }
    }

    private void configurationIdMustMatchPattern() {
        validateConfigurationId(uri.getHost());
    }

    private void validateConfigurationId(String configurationId) {
        if (configurationId == null || !configurationId.matches(XDIP.CONFIGURATION_ID_PATTERN)) {
            throw new InvalidXdipException(
                    "Configuration ids can only contain alphanumerics and dashes, but the id cannot " +
                            "start or end with a dash. The minimum length is 2 and maximum is 255.");
        }
    }
}
