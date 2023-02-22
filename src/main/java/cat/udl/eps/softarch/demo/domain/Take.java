package cat.udl.eps.softarch.demo.domain;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Take /*extends Batch */{
    @Id
    private Integer id;

}
