package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Seed;
import cat.udl.eps.softarch.demo.repository.SeedRepository;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

public class DeleteSeedStepDefs {
    @Autowired
    private StepDefs stepDefs;
    @Autowired
    private SeedRepository seedRepository;

    @Transactional
    @When("I delete the seed with scientific name \"([^\"]*)\"$")
    public void iDeleteSeedWithId(String scientificName) throws Throwable  {
        Optional<Seed> optionalSeed = seedRepository.findByScientificName(scientificName);
        stepDefs.result = stepDefs.mockMvc.perform(
                delete("/seeds/{id}", optionalSeed.isPresent() ? optionalSeed.get().getId() : "999")
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate())
        ).andDo(print());
    }
}
