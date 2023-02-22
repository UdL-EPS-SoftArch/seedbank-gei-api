package cat.udl.eps.softarch.demo.domain;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Entity
@Data
public class Batch {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;
    private Integer amount;
    private BigDecimal weight;
    private String location;
    private ZonedDateTime date;
}
