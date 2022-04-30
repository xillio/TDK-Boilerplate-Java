package nl.xillio.boilerplate.model.params;

import nl.xillio.boilerplate.model.datatypes.Config;
import nl.xillio.boilerplate.model.datatypes.RequestParameters;
import nl.xillio.boilerplate.model.datatypes.XDIP;

public record Params(
        Config config,
        XDIP xdip,
        RequestParameters requestParameters) {

}
