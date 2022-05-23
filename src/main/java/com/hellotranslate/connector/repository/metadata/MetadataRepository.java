package com.hellotranslate.connector.repository.metadata;

import com.hellotranslate.connector.model.Entity;
import com.hellotranslate.connector.model.XDIP;

import java.util.List;
import java.util.Map;

public sealed interface MetadataRepository permits MetadataRepositoryImpl {

    List<Entity> listChildren(XDIP xdip, Map<String, Object> config);

    List<XDIP> listReferences(XDIP xdip, Map<String, Object> config);

    Entity getEntityByXdip(XDIP xdip, Map<String, Object> config);
}
