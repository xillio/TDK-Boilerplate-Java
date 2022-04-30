package nl.xillio.boilerplate.model.datatypes;

import java.util.Arrays;

public record ProjectionIncludes(String[] projectionIncludes) {

    @Override
    public boolean equals(Object o)
    {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ProjectionIncludes that = (ProjectionIncludes) o;
        return Arrays.equals(projectionIncludes, that.projectionIncludes);
    }

    @Override
    public int hashCode()
    {
        return Arrays.hashCode(projectionIncludes);
    }

    @Override
    public String toString()
    {
        return "ProjectionIncludes{" +
               "projectionIncludes=" + Arrays.toString(projectionIncludes) +
               '}';
    }
}
