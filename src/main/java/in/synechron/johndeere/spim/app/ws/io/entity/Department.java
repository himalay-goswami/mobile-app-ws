package in.synechron.johndeere.spim.app.ws.io.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity(name = "department")
public class Department implements Serializable {

    private static final long serialVersionUID = 8488969592934810240L;

    @Id
    @GeneratedValue
    private long id;


}
