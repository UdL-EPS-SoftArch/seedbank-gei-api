package cat.udl.eps.softarch.demo.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Take extends Batch {
    @NotNull
    private ZonedDateTime date;

}
