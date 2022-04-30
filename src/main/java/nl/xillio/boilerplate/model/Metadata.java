package nl.xillio.boilerplate.model;

import nl.xillio.boilerplate.model.datatypes.Original;

public record Metadata(
        String id, //probably should be url
        String kind,
        Original original,
        Original modified // todo clarify
) implements ResultBodyComponent {

}
