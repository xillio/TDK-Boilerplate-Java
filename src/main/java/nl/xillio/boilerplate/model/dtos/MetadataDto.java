package nl.xillio.boilerplate.model.dtos;

import nl.xillio.boilerplate.model.dtos.datatype.OriginalDto;

public record MetadataDto(
        String id, //probably should be url
        String kind,
        OriginalDto original,
        OriginalDto modified // todo clarify
) {

}
