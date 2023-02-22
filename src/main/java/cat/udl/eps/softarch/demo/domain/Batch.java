package cat.udl.eps.softarch.demo.domain;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;


@Entity
@Data
public class Batch {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;


    @NotNull
    @Min(value = 1, message = "The minimal amount of a batch should be one")
    private Integer amount;

    @NotNull
    @DecimalMin(value = "0", message = "The minimal weight should be 0")
    private BigDecimal weight;

    @NotBlank
    @Length(min = 2, max = 30)
    private String location;

    private ZonedDateTime date = ZonedDateTime.now();

    @ManyToOne
    private Seed of;
}
