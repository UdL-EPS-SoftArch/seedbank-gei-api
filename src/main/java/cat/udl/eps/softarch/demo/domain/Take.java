package cat.udl.eps.softarch.demo.domain;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Data
public class Take /*extends Batch */{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank
    private ZonedDateTime date;

}
