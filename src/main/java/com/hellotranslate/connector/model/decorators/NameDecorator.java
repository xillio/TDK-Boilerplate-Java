package com.hellotranslate.connector.model.decorators;

import org.apache.maven.shared.utils.StringUtils;

public record NameDecorator(String systemName, String displayName) {
    public NameDecorator(String systemName, String displayName) {
        this.systemName = systemName;
        this.displayName = StringUtils.isEmpty(displayName) ? systemName : displayName;
    }
}
