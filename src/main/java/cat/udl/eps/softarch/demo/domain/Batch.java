package cat.udl.eps.softarch.demo.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Batch {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;
}
