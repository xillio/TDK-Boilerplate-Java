package com.hellotranslate.connector.repository.metadata;

import com.hellotranslate.connector.exception.MethodNotImplementedException;
import com.hellotranslate.connector.jsonrpc.EntityDto;
import com.hellotranslate.connector.model.XDIP;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public final class MetadataRepositoryImpl implements MetadataRepository { //todo pay attention at final modifier

    @Override
    public List<EntityDto> listChildren(XDIP xdip, Map<String, Object> config) {
        throw new MethodNotImplementedException();
    }

    @Override
    public List<XDIP> listReferences(XDIP xdip, Map<String, Object> config) {
        throw new MethodNotImplementedException();
    }
}
