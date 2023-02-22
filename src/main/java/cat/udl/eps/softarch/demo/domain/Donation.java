package cat.udl.eps.softarch.demo.domain;


import com.fasterxml.jackson.annotation.JsonIdentityReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

class Batch {}

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Donation extends Batch {
    //TODO: Delete this classes and use the ones in the domain package when implemented
    @Entity
    class Donor { @Id private Long id;}
    @Entity
    class Take { @Id private Long id;}
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JsonIdentityReference(alwaysAsId = true)
    private Donor donor;

    @OneToOne
    @JsonIdentityReference(alwaysAsId = true)
    private Take takenBy;

    public Donation(Donor donor, Take takenBy) {
        this.donor = donor;
        this.takenBy = takenBy;
    }

    protected Donation() {}
}