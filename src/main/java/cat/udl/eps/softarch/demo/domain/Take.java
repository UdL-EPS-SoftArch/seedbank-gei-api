package cat.udl.eps.softarch.demo.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Take extends Batch implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer takeId;
    @NotBlank
    private ZonedDateTime date;

}
