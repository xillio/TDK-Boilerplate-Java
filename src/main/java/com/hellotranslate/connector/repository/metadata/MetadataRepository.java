package com.hellotranslate.connector.repository.metadata;

import com.hellotranslate.connector.jsonrpc.response.dtos.EntityDto;
import com.hellotranslate.connector.jsonrpc.request.XDIP;

import java.util.List;
import java.util.Map;

public sealed interface MetadataRepository permits MetadataRepositoryImpl {

    List<EntityDto> listChildren(XDIP xdip, Map<String, Object> config);

    List<String> listReferences(XDIP xdip, Map<String, Object> config);

    EntityDto getEntityByXdip(XDIP xdip, Map<String, Object> config);
}
