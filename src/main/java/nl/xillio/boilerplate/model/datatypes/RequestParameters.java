package nl.xillio.boilerplate.model.datatypes;

public record RequestParameters(
        ProjectionScopes projectionScopes,
        ProjectionIncludes projectionIncludes,
        ProjectionExcludes projectionExcludes,
        Offset offset,
        Limit limit) {

}
