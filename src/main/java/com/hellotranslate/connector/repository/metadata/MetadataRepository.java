package com.hellotranslate.connector.repository.metadata;

import com.hellotranslate.connector.jsonrpc.EntityDto;
import com.hellotranslate.connector.model.XDIP;

import java.util.List;
import java.util.Map;

public sealed interface MetadataRepository permits MetadataRepositoryImpl {

    List<EntityDto> listChildren(String requestId, XDIP xdip, Map<String, Object> config);

    List<XDIP> listReferences(String requestId, XDIP xdip, Map<String, Object> config);
}
