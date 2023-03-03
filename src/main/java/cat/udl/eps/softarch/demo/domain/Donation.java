package cat.udl.eps.softarch.demo.domain;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Donation extends Batch {

    @ManyToOne
    @NotNull
    @JsonIdentityReference(alwaysAsId = true)
    private Donor donor;

    @OneToOne
    @JsonIdentityReference(alwaysAsId = true)
    private Take takenBy;
}
