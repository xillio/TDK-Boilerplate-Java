package nl.xillio.boilerplate.model.params;

import nl.xillio.boilerplate.model.datatypes.Config;
import nl.xillio.boilerplate.model.datatypes.RequestParameters;

public record Params(
        Config config,
        String xdip,
        RequestParameters requestParameters) {

}
