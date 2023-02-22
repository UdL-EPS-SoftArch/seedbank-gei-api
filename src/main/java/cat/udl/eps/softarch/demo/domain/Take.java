package cat.udl.eps.softarch.demo.domain;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.time.ZonedDateTime;
import java.util.Date;

@Entity
@Data
public class Take /*extends Batch */{
    @Id
    private Integer id;

    @NotBlank
    private ZonedDateTime date;

}
