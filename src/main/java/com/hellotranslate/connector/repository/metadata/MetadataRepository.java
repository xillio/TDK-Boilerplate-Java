package com.hellotranslate.connector.repository.metadata;

import com.hellotranslate.connector.jsonrpc.request.dtos.EntityDto;
import com.hellotranslate.connector.model.XDIP;

import java.util.List;
import java.util.Map;

public interface MetadataRepository {

    List<EntityDto> listChildren(
            XDIP xdip,
            Map<String, Object> config);

    List<XDIP> listReferences(
            XDIP xdip,
            Map<String, Object> config);
}
