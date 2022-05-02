package nl.xillio.boilerplate.model.dtos.params;

import nl.xillio.boilerplate.model.dtos.datatype.ConfigDto;
import nl.xillio.boilerplate.model.dtos.datatype.RequestParametersDto;

public record ParamsDto(
        ConfigDto configDto,
        String xdip,
        RequestParametersDto requestParametersDto) {

}
