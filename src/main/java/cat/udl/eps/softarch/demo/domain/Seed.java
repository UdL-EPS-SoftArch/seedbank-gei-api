package cat.udl.eps.softarch.demo.domain;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;


@Data
@Entity
public class Seed extends UriEntity<Long> {

    @Id
    @GeneratedValue()
    private Long id;

    @NotNull
    @NotBlank
    @Column(unique = true)
    private String scientificName;

    @NotNull
    @ElementCollection
    private List<String> commonName;

    @ManyToMany
    @JsonIdentityReference(alwaysAsId = true)
    private List<Seed> beneficialFor;
}
