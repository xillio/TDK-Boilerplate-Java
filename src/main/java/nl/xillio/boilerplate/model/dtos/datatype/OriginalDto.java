package nl.xillio.boilerplate.model.dtos.datatype;

import nl.xillio.boilerplate.model.dtos.decorators.*;

public record OriginalDto(
        ContainerDto containerDto,
        ContentTypeDto contentTypeDto,
        CreatedDto createdDto,
        ModifiedDto modifiedDto,
        NameDto nameDto,
        ParentDto parentDto) {

}
