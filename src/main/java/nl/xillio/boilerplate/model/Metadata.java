package nl.xillio.boilerplate.model;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.net.URL;

@Entity
@Getter
public class Metadata implements Serializable {

    @Id
    private URL id;
    private XDIP xdip;
    private String kind;
    private Object original;
    private Object modified;

    private Metadata(
            URL id,
            XDIP xdip,
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
