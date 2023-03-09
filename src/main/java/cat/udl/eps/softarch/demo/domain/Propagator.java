package cat.udl.eps.softarch.demo.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.ArrayList;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Propagator extends User {
    @ElementCollection
    @CollectionTable(name = "listOfTakes")
    private ArrayList<Take> takes = new ArrayList<>();
}
