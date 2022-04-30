package nl.xillio.boilerplate.model.datatypes;

import java.util.Arrays;

public record ProjectionScopes(String[] projectionScopes) {

    @Override
    public boolean equals(Object o)
    {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ProjectionScopes that = (ProjectionScopes) o;
        return Arrays.equals(projectionScopes, that.projectionScopes);
    }

    @Override
    public int hashCode()
    {
        return Arrays.hashCode(projectionScopes);
    }

    @Override
    public String toString()
    {
        return "ProjectionScopes{" +
               "projectionScopes=" + Arrays.toString(projectionScopes) +
               '}';
    }
}
