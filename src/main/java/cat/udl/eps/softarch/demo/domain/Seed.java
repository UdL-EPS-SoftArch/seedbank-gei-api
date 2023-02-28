package cat.udl.eps.softarch.demo.domain;

import lombok.Data;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;


@Data
@Entity
public class Seed {

    @Id
    @GeneratedValue()
    private Long id;

    @NotNull
    @NotBlank
    String scientificName;

    @NotNull
    @ElementCollection
    List<String> commonName;

}
