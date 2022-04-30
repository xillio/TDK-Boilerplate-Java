package nl.xillio.boilerplate.model;

import lombok.Getter;

import java.net.URL;

@Getter
public class Metadata {

    private URL id;
    private String xdip;
    private String kind;
    private Object original;
    private Object modified;

    private Metadata(
            URL id,
            String xdip,
            String kind,
            Object original,
            Object modified)
    {
        this.id = id;
        this.xdip = xdip;
        this.kind = kind;
        this.original = original;
        this.modified = modified;
    }

    public Metadata()
    {

    }
}
