package com.hellotranslate.connector.repository.content;

import com.hellotranslate.connector.model.Entity;
import com.hellotranslate.connector.model.XDIP;

import java.io.InputStream;
import java.util.Map;

public sealed interface ContentRepository permits ContentRepositoryImpl {

    InputStream downloadContent(XDIP xdip, Map<String, Object> config);

    Entity uploadContent(XDIP xdip, Map<String, Object> config, Entity entity, InputStream binaryContents);
}
