package cat.udl.eps.softarch.demo.domain;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Request extends Batch{
    @ManyToOne
    @NotNull
    @JsonIdentityReference(alwaysAsId = true)
    private Propagator propagator;

    @OneToOne
    @JsonIdentityReference(alwaysAsId = true)
    private Take fulfilledBy;
}
