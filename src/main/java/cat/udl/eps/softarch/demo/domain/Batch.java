package cat.udl.eps.softarch.demo.domain;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.ZonedDateTime;


@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Batch extends UriEntity<Long> {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

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
    @JsonIdentityReference(alwaysAsId = true)
    private Seed of;

}
