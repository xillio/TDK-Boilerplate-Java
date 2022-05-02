package nl.xillio.boilerplate.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Metadata implements Serializable {

    @Id
    private String id; // todo change type to url
    private String xdip; //todo change type to url
    private String kind;
    private Object original;
    private Object modified;
}
