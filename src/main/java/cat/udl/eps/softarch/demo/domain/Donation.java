package cat.udl.eps.softarch.demo.domain;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Donation extends Batch {

    @ManyToOne
    @JsonIdentityReference(alwaysAsId = true)
    private Donor donor;

    @OneToOne
    @JsonIdentityReference(alwaysAsId = true)
    private Take takenBy;
}
