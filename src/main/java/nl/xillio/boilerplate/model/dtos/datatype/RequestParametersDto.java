package nl.xillio.boilerplate.model.dtos.datatype;

import java.util.Arrays;
import java.util.Objects;

public record RequestParametersDto(
        String[] projectionScopes,
        String[] projectionIncludes,
        String[] projectionExcludes,
        Integer offset,
        Integer limit) {

    @Override
    public boolean equals(Object o)
    {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final RequestParametersDto that = (RequestParametersDto) o;
        return Arrays.equals(
                projectionScopes,
                that.projectionScopes) && Arrays.equals(
                projectionIncludes,
                that.projectionIncludes) && Arrays.equals(
                projectionExcludes,
                that.projectionExcludes) && Objects.equals(
                offset,
                that.offset) && Objects.equals(limit, that.limit);
    }

    @Override
    public int hashCode()
    {
        int result = Objects.hash(offset, limit);
        result = 31 * result + Arrays.hashCode(projectionScopes);
        result = 31 * result + Arrays.hashCode(projectionIncludes);
        result = 31 * result + Arrays.hashCode(projectionExcludes);
        return result;
    }

    @Override
    public String toString()
    {
        return "RequestParameters{" +
               "projectionScopes=" + Arrays.toString(projectionScopes) +
               ", projectionIncludes=" + Arrays.toString(projectionIncludes) +
               ", projectionExcludes=" + Arrays.toString(projectionExcludes) +
               ", offset=" + offset +
               ", limit=" + limit +
               '}';
    }
}
