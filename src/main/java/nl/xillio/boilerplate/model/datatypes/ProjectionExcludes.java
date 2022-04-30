package nl.xillio.boilerplate.model.datatypes;

import java.util.Arrays;

public record ProjectionExcludes(String[] projectionExcludes) {

    @Override
    public boolean equals(Object o)
    {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ProjectionExcludes that = (ProjectionExcludes) o;
        return Arrays.equals(projectionExcludes, that.projectionExcludes);
    }

    @Override
    public int hashCode()
    {
        return Arrays.hashCode(projectionExcludes);
    }

    @Override
    public String toString()
    {
        return "ProjectionExcludes{" +
               "projectionExcludes=" + Arrays.toString(projectionExcludes) +
               '}';
    }
}
