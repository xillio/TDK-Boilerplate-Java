package com.hellotranslate.connector.model.decorators;

public record ContentTypeDecorator(String systemName, String displayName) {
    public ContentTypeDecorator(String systemName, String displayName) {
        this.systemName = systemName;
        this.displayName = displayName.isEmpty() ? systemName : displayName;
    }
}
