package com.hellotranslate.connector.jsonrpc.response.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hellotranslate.connector.model.Entity;
import com.hellotranslate.connector.model.EntityReference;
import com.hellotranslate.connector.model.XDIP;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record EntityResult(
        Entity entity,
        @JsonProperty("path_children_entity")
        List<Entity> pathChildrenEntity,
        @JsonProperty("path_children_reference")
        List<EntityReference> pathChildrenReference) {

}
